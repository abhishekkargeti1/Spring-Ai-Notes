package com.spring.ai.controllers;

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
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService chatService;

	@GetMapping("/block-chat")
	public ResponseEntity<?> blockChat(@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "chatId", required = true) String conversionId) {

		String blockChatResponse = chatService.blockChat(query, conversionId);

		return ResponseEntity.status(HttpStatus.OK).body(blockChatResponse);

	}
	@GetMapping("/stream-chat")
	public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "chatId", required = true) String conversionId) {
		
		Flux<String> streamChatResponse = chatService.streamChat(query, conversionId);
		
		return ResponseEntity.status(HttpStatus.OK).body(streamChatResponse);
		
	}

}
