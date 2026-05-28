package com.spring.ai.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.ai.advisors.CustomAdvisor;

@Configuration
public class ProjectConfig {

	
	@Bean(name="OpenAiModel")
	public ChatClient openAiChatModel(OpenAiChatModel openAiChatModel,ChatMemory chatMemory) {
		
		MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
															.builder(chatMemory)
															.build();
		
		
		
		return ChatClient
				.builder(openAiChatModel)
				.defaultAdvisors(messageChatMemoryAdvisor,new CustomAdvisor(),new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of()))
				.build();
	}
	
	
	@Bean(name="OllamaAiModel")
	public ChatClient ollamaAiChatModel(OllamaChatModel ollamaAiChatModel,ChatMemory chatMemory) {
		MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
															.builder(chatMemory)
															.build();
		
		return ChatClient
				.builder(ollamaAiChatModel)
				.defaultAdvisors(messageChatMemoryAdvisor,new CustomAdvisor(),new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of()))
				.build();
	}
	
	
}
