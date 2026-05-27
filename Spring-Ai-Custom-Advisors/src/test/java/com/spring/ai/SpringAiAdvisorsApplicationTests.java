package com.spring.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.service.ChatService;

@SpringBootTest

class SpringAiAdvisorsApplicationTests {

	@Autowired
	private  ChatService chatService;
	
	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void chatService() {
		
		String query ="Java";
		String chatResponse = chatService.chatTemplate(query);
		//System.out.println("Chat Response is "+chatResponse);
	}

}
