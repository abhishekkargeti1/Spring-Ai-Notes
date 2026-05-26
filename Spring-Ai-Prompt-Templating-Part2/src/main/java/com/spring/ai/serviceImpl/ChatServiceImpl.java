package com.spring.ai.serviceImpl;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.services.ChatService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

	private ChatClient openAiChatModel;

	private ChatClient ollamaChatModel;
	@Value("classpath:/static/usermessage.st")
	private Resource userMessage;
	@Value("classpath:/static/systemmessage.st")
	private Resource systemMessage;

	public ChatServiceImpl(@Qualifier(value = "openAi") ChatClient openAiChatModel,
			@Qualifier(value = "ollamaAi") ChatClient ollamaChatModel) {
		super();
		this.openAiChatModel = openAiChatModel;
		this.ollamaChatModel = ollamaChatModel;
	}

	@Override
	public String chatService(String query) {
		log.info("User Query {}", query);
		String systemInfo = "Your are a expert Java developer . Always write code in Java {query}";
		String aiResponse = ollamaChatModel.prompt().user(u -> u.text(systemInfo).param("query", query)).call()
				.content();

		return aiResponse;
	}

	@Override
	public String chatTemplateService(String query) {

		log.info("User Query is {}",query);
		PromptTemplate temple = PromptTemplate.builder()
								.template(query)
								.build();

//		String renderTemplate = temple.render(Map.of(
//				"query",query
//				));
		
		String renderTemplate = temple.render();
		log.info("Rendered Template is {}",renderTemplate);
		Prompt prompt = new Prompt(renderTemplate);
		String systemInfo = "Your are a expert Java developer . Always write code in Java ";
		String aiResponse = openAiChatModel
							.prompt(prompt)
							.system(systemInfo)
							.call()
							.content();
		
		return aiResponse;
	}

	
	@Override
	public String chatTemplateService2(String query) {
		log.info("User Query {}",query);
		
		
		PromptTemplate userPromptTemplate = PromptTemplate.builder()
				.template("Tell me about this {teachName}")
				.build();
		SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder()
				.template("Your are a expert Software developer . Always write code effective and efficient code")
				.build();
		
		var userMessage = userPromptTemplate.createMessage(Map.of(
				"teachName",query
				));
		var systemMessage = systemPromptTemplate.createMessage();
		
//		var userMessage = userPromptTemplate.render(Map.of(
//				"teachName",query
//				));
//		var systemMessage = systemPromptTemplate.render();
//		
		
//      return openAiChatModel.prompt().user(userMessage).system(systemMessage).call().content();
		
		Prompt prompt = new Prompt(userMessage,systemMessage);
		return openAiChatModel.prompt(prompt).call().content();
	}

	
	@Override
	public String chatTemplateService3(String query,String query2) {
		log.info("User Query {}",query);
		// fluent  Api 
		return openAiChatModel.prompt()
				.user(user -> user.text("Tell me about this {techName} and also about this frmaework {frameWorkName}").params(Map.of ("techName", query ,"frameWorkName",query2)))
				.system(system -> system.text("Your are a expert Software developer . Always write code effective and efficient code"))
				.call()
				.content();
	}

	@Override
	public String chatTemplateService4(String query) {
		return ollamaChatModel.prompt()
				.user(user -> user.text(userMessage).param("query", query))
				.system(system -> system.text(systemMessage))
				.call()
				.content();
	}
	
	
	
	
	
	
	
}
