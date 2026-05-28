package com.spring.ai.service;

import reactor.core.publisher.Flux;

public interface ChatService {
	
	
	public String chat(String query,String conversationId);
	public Flux<String> streamChat(String query,String conversationId);

}
