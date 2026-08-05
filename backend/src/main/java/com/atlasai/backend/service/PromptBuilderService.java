package com.atlasai.backend.service;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    public String buildPrompt(
            String query,
            String context
    ) {

        return """
                You are Atlas AI.

                You are an enterprise AI assistant.

                Only answer using the provided company knowledge.

                If the answer cannot be found,
                say you do not know.

                Never invent information.

                ----------------------------

                COMPANY KNOWLEDGE

                %s

                ----------------------------

                USER QUESTION

                %s

                ----------------------------

                Provide a concise professional answer.
                """.formatted(
                context,
                query
        );

    }

}