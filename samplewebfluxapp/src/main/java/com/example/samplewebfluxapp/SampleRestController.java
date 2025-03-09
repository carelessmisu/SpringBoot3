package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SampleRestController {

	@RequestMapping("/flux")
	public Mono<String> flux() {
		return Mono.just("Hello Flux(Mono).");
	}

	@RequestMapping("/flux2")
	public Flux<String> flux2() {
		return Flux.just("Hello Flux.", "これはFluxのサンプルです。");
	}

	@RequestMapping("/post")
	public Mono<Post> post() {
		Post post = new Post(0, 0, "dummy", "dummy message...");
		return Mono.just(post);
	}
}
