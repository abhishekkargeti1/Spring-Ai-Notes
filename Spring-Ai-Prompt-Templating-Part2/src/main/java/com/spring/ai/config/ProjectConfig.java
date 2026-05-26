package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

	
	
	@Bean(name="openAi")
	public ChatClient openAiChatService(OpenAiChatModel chatModel) {
		return ChatClient.builder(chatModel).build();
	}

	@Bean(name="ollamaAi")
	public ChatClient ollamaChatService(OllamaChatModel chatModel) {
		return ChatClient.builder(chatModel).build();
	}

}
