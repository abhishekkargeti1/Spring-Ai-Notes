package com.spring.ai.service;

import reactor.core.publisher.Flux;

public interface ChatService {
	
	
	public String chatTemplate(String query);
	public Flux<String>  streamChat(String query);

}
