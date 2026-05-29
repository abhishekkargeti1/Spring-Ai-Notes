package com.spring.ai.advisors;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import reactor.core.publisher.Flux;

public class CustomAdvisor implements CallAdvisor, StreamAdvisor {

	@Override
	public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
			StreamAdvisorChain streamAdvisorChain) {
		// TODO Auto-generated method stub

		Flux<ChatClientResponse> nextStream = streamAdvisorChain.nextStream(chatClientRequest);

		return nextStream;
	}

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
		// TODO Auto-generated method stub

		ChatClientResponse nextCall = callAdvisorChain.nextCall(chatClientRequest);

		return nextCall;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
