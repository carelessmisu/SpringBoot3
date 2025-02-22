package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
public class SampleBootApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleBootApp1Application.class, args);

	}

	@RequestMapping("/")
	public String index() {
		return "Hello Spring Boot 3!!!";
	}

}
