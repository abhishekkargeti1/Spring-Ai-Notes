package com.spring.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.services.ChatService;

@SpringBootTest
class SpringAiPromptTemplatingPart2ApplicationTests {

	@Autowired
	private ChatService chatService;

	@Test
	void contextLoads() {
	}

	@Test
	public void chat() {
		System.out.println("Test ");
		String query = "Tell me about Programming Languages?";
		String chatServiceResponse = chatService.chatTemplateService(query);
		System.out.println("Chat Response "+chatServiceResponse);
		//return chatServiceResponse;
	}
	@Test
	public void chat1() {
		System.out.println("Test 1");
		String query = "GO";
		String chatServiceResponse = chatService.chatTemplateService2(query);
		System.out.println("Chat Response "+chatServiceResponse);
		//return chatServiceResponse;
	}
	@Test
	public void chat2() {
		System.out.println("Test 2");
		String query = "JavaScript";
		String query2 ="ReactJS";
		String chatServiceResponse = chatService.chatTemplateService3(query,query2);
		System.out.println("Chat Response "+chatServiceResponse);
		//return chatServiceResponse;
	}
	@Test
	public void chat3() {
		System.out.println("Test 3");
		String query = "JavaScript";
		String chatServiceResponse = chatService.chatTemplateService4(query);
		System.out.println("Chat Response "+chatServiceResponse);
		//return chatServiceResponse;
	}

}
