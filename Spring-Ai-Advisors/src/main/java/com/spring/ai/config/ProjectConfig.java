package com.spring.ai.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

	@Bean(name="openAiModel")
	public ChatClient openAiChatModel(OpenAiChatModel openAiChatModel) {
		return ChatClient
				.builder(openAiChatModel)
				.defaultAdvisors(new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of()))
				.build();
		
	}
	
	
	@Bean(name="ollamaAiModel")
	public ChatClient ollamaAiChatModel(OllamaChatModel ollamaChatModel) {
		return ChatClient.builder(ollamaChatModel).build();
	}
	
	
	
	
}
