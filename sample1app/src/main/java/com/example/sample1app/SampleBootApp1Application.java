package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleBootApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleBootApp1Application.class, args);

	}
}
