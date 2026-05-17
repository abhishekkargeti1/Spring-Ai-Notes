package com.spring.ai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatController {

	private ChatClient chatClient;

	public ChatController(ChatClient.Builder chatClient) {
		this.chatClient = chatClient.build();
	}

	@GetMapping("/chat")
	public ResponseEntity<?> chat(@RequestParam("q") String query) {
		log.info("User Query is {} ", query);

		var resultResponse = chatClient.prompt(query).call().content();

		return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
	}

}
