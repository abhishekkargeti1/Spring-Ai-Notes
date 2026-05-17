package com.spring.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/chat")
	public ResponseEntity<?> chat(@RequestParam("q") String query) {
		log.info("User Query is {} ", query);

		var resultResponse = chatService.chat(query);

		return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
	}

}
