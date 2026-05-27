package com.spring.ai.advisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import reactor.core.publisher.Flux;

public class CustomAdvisor implements CallAdvisor, StreamAdvisor{

	private Logger logger = LoggerFactory.getLogger(CustomAdvisor.class);
	
	@Override
	public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
			StreamAdvisorChain streamAdvisorChain) {
		
		
		logger.info("My Request "+chatClientRequest
				.prompt()
				.getContents());
		
		Flux<ChatClientResponse> chatClientResponse = streamAdvisorChain.nextStream(chatClientRequest);
		
		return chatClientResponse;
	}

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
		// TODO Auto-generated method stub
		
		//logger.info("Client Request {}",chatClientRequest);
		
		logger.info("My Request "+chatClientRequest
								.prompt()
								.getContents());

		ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
		
		
		//logger.info("Client Response {}",chatClientResponse);
		logger.info("My Response "+chatClientResponse
								.chatResponse()
								.getResult()
								.getOutput()
								.getText());
//		Request Total Token
		logger.info("Prompt Token "+chatClientResponse
								.chatResponse()
								.getMetadata()
								.getUsage()
								.getPromptTokens());
//		Response Total Token
		logger.info("Completion Token "+chatClientResponse
								.chatResponse()
								.getMetadata()
								.getUsage()
								.getCompletionTokens());
//		Total Token = Response + Request Token
		logger.info("Token Usage "+chatClientResponse
								.chatResponse()
								.getMetadata()
								.getUsage()
								.getTotalTokens());
			
		return chatClientResponse;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
