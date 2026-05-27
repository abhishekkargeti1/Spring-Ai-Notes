package com.spring.ai.serviceImpl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

import reactor.core.publisher.Flux;

@Service
public class ChatServiceImpl implements ChatService {

	private ChatClient openAi;
	private ChatClient ollamaAi;
	
	@Value("classpath:/static/userMessage.st")
	private Resource userMessage;
	
	@Value("classpath:/static/systemMessage.st")
	private Resource systemMessage;
	
	

	public ChatServiceImpl(@Qualifier(value ="openAiModel")  ChatClient openAi, @Qualifier(value ="ollamaAiModel")ChatClient ollamaAi) {
		super();
		this.openAi = openAi;
		this.ollamaAi = ollamaAi;
	}

	@Override
	public String chatTemplate(String query) {

		return openAi
				.prompt()
				.user(u -> u.text(userMessage).param("userQuery", query))
				.system(s -> s.text(systemMessage))
				.call()
				.content();
//		return ollamaAi
//				.prompt()
//				.user(u -> u.text(userMessage).param("userQuery", query))
//				.system(s -> s.text(systemMessage))
//				.call()
//				.content();
	}

	@Override
	public Flux<String>  streamChat(String query) {
		// TODO Auto-generated method stub
		return ollamaAi
				.prompt()
				.user(u -> u.text(userMessage).param("userQuery", query))
				.system(s -> s.text(systemMessage))
				.stream()
				.content();
//		return openAi
//				.prompt()
//				.user(u -> u.text(userMessage).param("userQuery", query))
//				.system(s -> s.text(systemMessage))
//				.stream()
//				.content();
	}
	
	
	

}
