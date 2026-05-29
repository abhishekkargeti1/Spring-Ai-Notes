package com.spring.ai.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.ai.advisors.CustomAdvisor;

@Configuration
public class ProjectConfig {

	
	
	@Bean
	public ChatMemory chatMemory(JdbcChatMemoryRepository chatMemoryRepository) {
		return MessageWindowChatMemory
				.builder()
				.chatMemoryRepository(chatMemoryRepository)
				.maxMessages(10)
				.build();
	}
	
	
	@Bean(name="openAiModel")
	public ChatClient openAiChatModel(OpenAiChatModel openAiChatModel,ChatMemory chatMemory) {
		
		MessageChatMemoryAdvisor advisor =  MessageChatMemoryAdvisor.builder(chatMemory).build();

		return ChatClient.builder(openAiChatModel)
				.defaultAdvisors(new CustomAdvisor(),advisor,new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of()))
				.build();

	}
	@Bean(name="ollamaAiModel")
	public ChatClient ollamaAiChatModel(OllamaChatModel ollamaChatModel,ChatMemory chatMemory) {
		
		MessageChatMemoryAdvisor advisor =  MessageChatMemoryAdvisor.builder(chatMemory).build();

		return ChatClient.builder(ollamaChatModel)
				.defaultAdvisors(new CustomAdvisor(),advisor,new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of()))
				.build();
		
	}
	
	

}
