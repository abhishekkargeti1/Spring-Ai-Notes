package com.spring.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.helper.Helper;
import com.spring.ai.service.ChatService;

@SpringBootTest
class SpringAiVectorRagImplementationApplicationTests {

	@Autowired
	private ChatService chatService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void saveVectorData() {
		System.out.println("Data Saving Started");
		chatService.saveData(Helper.getData());
		System.out.println("Data Saving Completed");
	}
	
	
	
	
}
