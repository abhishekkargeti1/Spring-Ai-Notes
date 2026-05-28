package com.spring.ai.serviceImpl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

import reactor.core.publisher.Flux;

@Service
public class ChatServiceImpl implements ChatService {

	private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

	private ChatClient openAi;
	private ChatClient ollamaAi;
	@Value("classpath:/static/user_prompt_template.st")
	private Resource userPromptMessage;
	@Value("classpath:/static/system_prompt_template.st")
	private Resource systemPromptMessage;
	
	
	public ChatServiceImpl(@Qualifier(value="OpenAiModel") ChatClient openAi,@Qualifier(value="OllamaAiModel") ChatClient ollamaAi) {
		super();
		this.openAi = openAi;
		this.ollamaAi = ollamaAi;
	}



	@Override
	public Flux<String> streamChat(String query,String conversationId) {
		// TODO Auto-generated method stub

		logger.info("User Query in Service Layer {}", query);

		
		PromptTemplate userpromptTemplate = PromptTemplate
											.builder()
											.resource(userPromptMessage)
											.build();
			
		SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate
											.builder()
											.resource(systemPromptMessage)
											.build();
		
		
		String renderedUserMessage = userpromptTemplate.render(Map.of(
				"userQuery" , query
				));
		
		
		String renderedSystemMessage = systemPromptTemplate.render();
		
		return openAi
				.prompt()
				.user(u -> u.text(renderedUserMessage))
				.advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
				.system(s -> s.text(renderedSystemMessage))
				.stream()
				.content();
	}
	
	@Override
	public String chat(String query,String conversationId) {
		// TODO Auto-generated method stub

		logger.info("User Query in Service Layer {}", query);

		
		PromptTemplate userpromptTemplate = PromptTemplate
											.builder()
											.resource(userPromptMessage)
											.build();
			
		SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate
											.builder()
											.resource(systemPromptMessage)
											.build();
		
		
		String renderedUserMessage = userpromptTemplate.render(Map.of(
				"userQuery" , query
				));
		
		
		String renderedSystemMessage = systemPromptTemplate.render();
		
		return ollamaAi
				.prompt()
				.user(u -> u.text(renderedUserMessage))
				.advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
				.system(s -> s.text(renderedSystemMessage))
				.call()
				.content();
	}

}
