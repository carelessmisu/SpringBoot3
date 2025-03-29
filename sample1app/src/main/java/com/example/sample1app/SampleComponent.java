package com.example.sample1app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SampleComponent {

    @Value("${sampleapp.samplecomponent.message}")
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String message() {
        return message;
    }
}
