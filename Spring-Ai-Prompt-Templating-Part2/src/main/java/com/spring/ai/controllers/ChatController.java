package com.spring.ai.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.services.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService service;

	@GetMapping("/chat")
	public ResponseEntity<?> chat(@RequestParam("q") String query) {

		String chatServiceResponse = service.chatTemplateService(query);

		return ResponseEntity.status(HttpStatus.OK).body(chatServiceResponse);
	}

}
