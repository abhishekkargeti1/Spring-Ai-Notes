package com.spring.ai.service;

import java.util.List;

import com.spring.ai.entities.Tut;

public interface ChatService {
	
	String chat(String query);
	List<Tut> chat1(String query);
	

}
