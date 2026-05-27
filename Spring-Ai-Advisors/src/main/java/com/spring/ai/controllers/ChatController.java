package com.spring.ai.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService chatService;

	@GetMapping("/chat")
	public ResponseEntity<?> chat(@RequestParam(name = "q", required = true) String query) {

		String chatResponse = chatService.chatTemplate(query);

		return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
	}

}
