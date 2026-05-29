package com.spring.ai.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

import reactor.core.publisher.Flux;
@Service
public class ChatServiceImpl implements ChatService{

	private Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	private ChatClient openAI;
	private ChatClient ollamaAI;
	@Value("classpath:/static/user_message_template.st")
	private Resource userMessage;
	@Value("classpath:/static/system_message_template.st")
	private Resource systemMessage;
	
	public ChatServiceImpl(@Qualifier(value="openAiModel") ChatClient openAI, @Qualifier(value="ollamaAiModel") ChatClient ollamaAI) {
		super();
		this.openAI = openAI;
		this.ollamaAI = ollamaAI;
	}

	@Override
	public String blockChat(String query, String conversionId) {
		// TODO Auto-generated method stub
		logger.info("User Query is "+query+" Conversion Id "+conversionId);
		
		return ollamaAI
				.prompt()
				.user(u->u.text(userMessage).param("userQuery",query))
				.system(s->s.text(systemMessage))
				.advisors(a -> a.param(ChatMemory.CONVERSATION_ID,conversionId))
				.call()
				.content();
	}

	@Override
	public Flux<String> streamChat(String query, String conversionId) {
		// TODO Auto-generated method stub
		logger.info("User Query is "+query+" Conversion Id "+conversionId);

		return openAI
				.prompt()
				.user(u->u.text(userMessage).param("userQuery",query))
				.system(s->s.text(systemMessage))
				.advisors(a -> a.param(ChatMemory.CONVERSATION_ID,conversionId))
				.stream()
				.content();
	}

	
}
