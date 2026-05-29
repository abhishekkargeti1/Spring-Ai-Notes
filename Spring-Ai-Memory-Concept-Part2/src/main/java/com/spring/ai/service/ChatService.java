package com.spring.ai.service;

import reactor.core.publisher.Flux;

public interface ChatService {

	public String blockChat(String query,String conversionId);
	public Flux<String> streamChat(String query,String conversionId);
}
