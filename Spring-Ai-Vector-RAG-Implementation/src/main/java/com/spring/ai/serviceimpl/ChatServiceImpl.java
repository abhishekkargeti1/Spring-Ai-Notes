package com.spring.ai.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

import reactor.core.publisher.Flux;
@Service
public class ChatServiceImpl implements ChatService {

	
	private ChatClient openAi;
	//private ChatClient ollamaAi;
	@Value("classpath:/static/user_message_template.st")
	private Resource userMessage;
	@Value("classpath:/static/system_message_template.st")
	private Resource systemMessage;
	
	private VectorStore vectorStore;
	
	private Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	

	public ChatServiceImpl(@Qualifier(value="openAiModel")ChatClient openAi, VectorStore vectorStore) {
		super();
		this.openAi = openAi;
		this.vectorStore = vectorStore;
	}

	@Override
	public String blockChat(String query, String conversionId) {
		
		SearchRequest searchRequest = SearchRequest
										.builder()
										.query(query)
										.topK(5)
										.similarityThreshold(0.5)
										.build();

		List<Document> similaritySearch = vectorStore.similaritySearch(searchRequest);

		logger.info("Similar Searched Document List {}",similaritySearch);



		List<String> similarSearchList = similaritySearch.stream().map(item -> item.getText()).toList();

		logger.info("Similar Searched  List {}",similarSearchList);

		String contextForAi = String.join(" , ", similarSearchList);
		
		logger.info("Ai Context  {}",contextForAi);
		
		
		
		// TODO Auto-generated method stub
		return openAi
				.prompt()
				.advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversionId))
				.user(u -> u.text(userMessage).param("userQuery", query))
				.system(s -> s.text(systemMessage).param("document", contextForAi))
				.call()
				.content();
	}

	@Override
	public Flux<String> streamChat(String query, String conversationId) {
		
		
		SearchRequest searchRequest = SearchRequest
										.builder()
										.query(query)
										.topK(5)
										.similarityThreshold(0.5)
										.build();
		
		List<Document> similaritySearch = vectorStore.similaritySearch(searchRequest);
		
		logger.info("Similar Searched Document List {}",similaritySearch);
		
		
		
		List<String> similarSearchList = similaritySearch.stream().map(item -> item.getText()).toList();
		
		logger.info("Similar Searched  List {}",similarSearchList);

		String contextForAi = String.join(" , ", similarSearchList);

		
		logger.info("Ai Context  {}",contextForAi);
		
		// TODO Auto-generated method stub
		return openAi
				.prompt()
				.advisors(a ->a.param(ChatMemory.CONVERSATION_ID, conversationId))
				.user(u ->u.text(userMessage).param("userQuery", query))
				.system(s -> s.text(systemMessage).param("document", contextForAi))
				.stream()
				.content();
	}

	@Override
	public void saveData(List<String> dataList) {
		List<Document> documentList = dataList
										.stream()
										.map(data -> new Document(data))
										.collect(Collectors.toList());
		vectorStore.add(documentList);
	}
	
	
	

}
