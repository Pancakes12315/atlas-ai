package com.atlasai.backend.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class OllamaService {

    private final RestClient restClient;

    public OllamaService() {

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:11434")
                .build();

    }

    public String generate(String prompt) {

        Map<String, Object> request = Map.of(

                "model", "llama3.2:3b",

                "prompt", prompt,

                "stream", false

        );

        Map response =
                restClient.post()
                        .uri("/api/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(request)
                        .retrieve()
                        .body(Map.class);

        if (response == null) {

            return "Atlas AI could not generate a response.";

        }

        Object answer = response.get("response");

        return answer == null
                ? "No response generated."
                : answer.toString();

    }

}