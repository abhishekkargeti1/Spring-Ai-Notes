package com.spring.ai.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.service.ChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService chatService;
	private Logger logger = LoggerFactory.getLogger(ChatController.class);

	@GetMapping("/chat")
	public ResponseEntity<Flux<String>> chat(@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "conversationId", required = true) String conversationId) {

		logger.info("User Query Recived in Controller {} ", query);

		Flux<String> chatResponse = chatService.streamChat(query, conversationId);


		return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
	}

	@GetMapping("/chat1")
	public ResponseEntity<?> chat1(@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "conversationId", required = true) String conversationId) {

		logger.info("User Query Recived in Controller {} ", query);

		// Flux<String> chatResponse = chatService.streamChat(query);
		String chatResponse = chatService.chat(query,conversationId);

		return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
	}

}
