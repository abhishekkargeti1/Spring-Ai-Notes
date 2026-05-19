package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

	@Bean(name="OllamaChatClient")
	public ChatClient ollamaChatClient(OllamaChatModel chatModel) {
		return ChatClient.builder(chatModel)
				.defaultSystem("You are a helpful coding assistant .You are an exerpt coder ")
				.defaultOptions(OllamaChatOptions.builder()
						.temperature(0.2)
						.model("codellama:latest")
						.maxTokens(200)
						).build();
	}
	@Bean(name="OpenAiChatClient")
	public ChatClient openAiChatClient(OpenAiChatModel chatModel) {
		
		   OpenAiChatOptions.Builder options = OpenAiChatOptions.builder()
		            .model("gpt-4o-mini")
		            .temperature(0.2)
		            .maxTokens(500);
		
		
		
		return ChatClient.builder(chatModel).defaultSystem("You are a helpful coding assistant .You are an exerpt coder ").defaultOptions(options).build();
	}
	
	
}
