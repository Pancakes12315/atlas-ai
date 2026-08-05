package com.atlasai.backend.service;

import com.atlasai.backend.model.DocumentChunk;
import com.atlasai.backend.model.RankedDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ContextBuilderService {

    public String buildContext(
        List<RankedDocument> documents,
        String query
    ){

        List<DocumentChunk> chunks = new ArrayList<>();

        for (RankedDocument document : documents) {

            String[] paragraphs = document
                    .getContent()
                    .split("\\n\\s*\\n");

            if (paragraphs.length == 0) {

                paragraphs = new String[]{
                        document.getContent()
                };

            }

            for (String paragraph : paragraphs) {

                chunks.add(

                        new DocumentChunk(
                                document.getName(),
                                paragraph.trim(),
                                calculateChunkScore(
                                query,
                                paragraph
                        )
                        )

                );

            }

        }

        chunks.sort(
                Comparator.comparingInt(DocumentChunk::getScore)
                        .reversed()
        );

        int highestScore = chunks.isEmpty()
        ? 0
        : chunks.get(0).getScore();

        if (highestScore == 0) {

            StringBuilder fallback = new StringBuilder();

            fallback.append(
                    "No exact match was found for your search.\n\n"
            );

            fallback.append(
                    "You may find these documents helpful:\n\n"
            );

            documents.stream()
                    .limit(3)
                    .forEach(document ->

                            fallback.append("• ")
                                    .append(document.getName())
                                    .append("\n")

                    );

            return fallback.toString();

        }
        StringBuilder context = new StringBuilder();

        chunks.stream()
                .limit(3)
                .forEach(chunk -> {

                    context.append("Source: ")
                            .append(chunk.getDocumentName())
                            .append("\n");

                    context.append(chunk.getText());

                    context.append("\n\n");

                });

        return context.toString();

    }

    private int calculateChunkScore(
        String query,
        String paragraph
    ) {

        String[] words =
                query.toLowerCase().split("\\s+");

        String text =
                paragraph.toLowerCase();

        int score = 0;

        for (String word : words) {

            if (text.contains(word)) {

                score++;

            }

        }

        return score;

    }

}