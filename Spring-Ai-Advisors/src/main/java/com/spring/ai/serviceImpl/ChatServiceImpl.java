package com.spring.ai.serviceImpl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

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
	}

}
