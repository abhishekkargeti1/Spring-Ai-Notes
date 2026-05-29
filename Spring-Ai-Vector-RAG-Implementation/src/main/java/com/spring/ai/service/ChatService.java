package com.spring.ai.service;

import java.util.List;

import reactor.core.publisher.Flux;

public interface ChatService {
	
	public String blockChat(String query,String conversionId);
	public Flux<String>streamChat(String query,String conversationId);
	
	public void saveData(List<String> dataList);

}
