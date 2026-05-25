package com.spring.ai.serviceImpl;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.ai.entities.Tut;
import com.spring.ai.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
	
	
	private ChatClient ollamaAiClient;
	private ChatClient openAiClient;

	public ChatServiceImpl(@Qualifier("OllamaChatClient") ChatClient ollamaAiClient,
			@Qualifier("OpenAiChatClient") ChatClient openAiClient) {
		this.ollamaAiClient = ollamaAiClient;
		this.openAiClient = openAiClient;
	}

	@Override
	public String chat(String query) {
		Prompt prompt = new Prompt(query);
		String queryStr = "Are are a java expert . Please write all the query in go language . {query}";
		String contentResponse = ollamaAiClient.prompt(prompt).user(u -> u.text(queryStr).param("query", query)).call().content();
		return contentResponse;
	}

	@Override
	public List<Tut> chat1(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
