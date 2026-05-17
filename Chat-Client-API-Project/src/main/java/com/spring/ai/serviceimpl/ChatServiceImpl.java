package com.spring.ai.serviceimpl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import com.spring.ai.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

	private ChatClient chatClient;

	public ChatServiceImpl(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@Override
	public String chat(String query) {
		String prompt = "Tell me about Virat Kohli";
		var response="" ;
		//response = chatClient.prompt().user(prompt).system("As a expert in cricket").call().content();
		//response = chatClient.prompt(prompt).call().content();
		
		Prompt prompt1 = new Prompt(query);
		
		//response = chatClient.prompt(prompt1).call().content();
		response = chatClient.prompt(prompt1).call().chatResponse().getResult().getOutput().getText();
		//ChatResponseMetadata metaData = chatClient.prompt(prompt1).call().chatResponse().getMetadata();
		//log.info("MetaData {}",metaData);
		return response;
	}

}
