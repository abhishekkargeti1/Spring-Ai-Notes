package com.spring.ai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
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

	private ChatClient openAiChatClient;
	private ChatClient ollamaChatClient;

	public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatModel,
			@Qualifier("ollamaAiChatClient") ChatClient ollamaChatModel) {
		this.openAiChatClient = openAiChatModel;
		this.ollamaChatClient = ollamaChatModel;
	}

	@GetMapping("/chat")
	public ResponseEntity<?> chat(@RequestParam("q") String query) {

		log.info("User Query {} ", query);

		var modelResponse = ollamaChatClient.prompt(query).call().content();

		return ResponseEntity.status(HttpStatus.OK).body(modelResponse);
	}

}
