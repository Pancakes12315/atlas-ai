package com.atlasai.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AnswerGenerationService {

    public String generateAnswer(String prompt) {

        String answer = prompt;

        answer = answer.replaceAll("(?s).*USER QUESTION", "");

        answer = answer.replace("----------------------------", "");

        answer = answer.replace("Provide a concise professional answer.", "");

        answer = answer.trim();

        return answer;

    }

}