package com.spring.ai.serviceimpl;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
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
	public Tut chat(String query) {

		// var response = "";

		// response = chatClient.prompt().user(prompt).system("As a expert in
		// cricket").call().content();
		// response = chatClient.prompt(prompt).call().content();

		// Prompt prompt1 = new Prompt(query);
		Prompt prompt1 = new Prompt(query,
				OpenAiChatOptions.builder().temperature(0.5).model("gpt-4o-mini").maxTokens(100).build());

		// response = chatClient.prompt(prompt1).call().content();
		// response =
		// chatClient.prompt(prompt1).call().chatResponse().getResult().getOutput().getText();
		// ChatResponseMetadata metaData =
		// chatClient.prompt(prompt1).call().chatResponse().getMetadata();

		// log.info("MetaData {}",metaData);

		Tut entityDetails = openAiClient.prompt("""
				Generate ONLY valid JSON.

				Rules:
				1. Do not write explanations
				2. Do not write markdown
				3. Do not write ```json
				4. Return raw JSON only

				Expected JSON format:

				{
				  "title": "",
				  "content": "",
				  "createdAt": "",
				  "createdYear": ""
				}

				User Query:
								""" + prompt1).call().entity(Tut.class);

		return entityDetails;
	}

	@Override
	public List<Tut> chat1(String query) {
		Prompt prompt = new Prompt(query);
//		Prompt prompt = new Prompt(query,
//				OpenAiChatOptions.builder().temperature(0.5).model("gpt-4o-mini").maxTokens(100)
//						.build());
		List<Tut> listRepsonse = openAiClient.prompt(prompt).call().entity(new ParameterizedTypeReference<List<Tut>>() {
		});

		return listRepsonse;
	}

}
