package com.atlasai.backend.service;

import com.atlasai.backend.model.RankedDocument;
import com.atlasai.backend.model.SearchResponse;
import com.atlasai.backend.model.Source;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SearchService {

    private final KnowledgeBaseService knowledgeBaseService;
    private final ContextBuilderService contextBuilderService;
    private final SynonymService synonymService;
    private final PromptBuilderService promptBuilderService;
    private final OllamaService ollamaService;

    public SearchService(
        KnowledgeBaseService knowledgeBaseService,
        ContextBuilderService contextBuilderService,
        SynonymService synonymService,
        PromptBuilderService promptBuilderService,
        OllamaService ollamaService
        ) {
        this.knowledgeBaseService = knowledgeBaseService;
        this.contextBuilderService = contextBuilderService;
        this.synonymService = synonymService;
        this.promptBuilderService = promptBuilderService;
        this.ollamaService = ollamaService;
        }

    public SearchResponse search(String query) {

        List<RankedDocument> rankedDocuments =
                knowledgeBaseService
                        .getDocuments()
                        .stream()
                        .map(document ->
                                new RankedDocument(
                                        document.getName(),
                                        document.getContent(),
                                        calculateScore(
                                                query,
                                                document.getContent()
                                        )
                                )
                        )
                        .filter(document -> document.getScore() > 0)
                        .sorted(
                                Comparator.comparingInt(
                                        RankedDocument::getScore
                                ).reversed()
                        )
                        .toList();

        List<Source> sources =
                rankedDocuments
                        .stream()
                        .limit(3)
                        .map(document ->
                                new Source(
                                        document.getName(),
                                        "Knowledge Base Document",
                                        "Recently Updated",
                                        document.getScore()
                                )
                        )
                        .toList();

        String answer;

        if (!rankedDocuments.isEmpty()) {

            List<RankedDocument> topDocuments =
                    rankedDocuments.stream()
                            .limit(3)
                            .toList();

            String context =
                    contextBuilderService.buildContext(
                            topDocuments,
                            query
                    );

            String prompt =
                promptBuilderService.buildPrompt(
                        query,
                        context
                );

            answer =
                ollamaService.generate(
                        prompt
                );

        } else {

            answer =
                    "Atlas AI could not find relevant information for \""
                            + query
                            + "\".";

        }

        return new SearchResponse(

                answer,

                rankedDocuments.isEmpty()
                        ? 50
                        : rankedDocuments.get(0).getScore(),

                sources,

                List.of(
                        "Analyzed document relevance",
                        "Compared query keywords",
                        "Expanded search using related terms",
                        "Ranked knowledge sources"
                ),

                List.of(
                        "Review matching documents",
                        "Explore related knowledge",
                        "Ask a follow-up question"
                )

        );

    }

    private int calculateScore(
            String query,
            String content
    ) {

        String[] words =
                query.toLowerCase().split("\\s+");

        String document =
                content.toLowerCase();

        int matches = 0;

        for (String word : words) {

            if (document.contains(word)) {
                matches += 2;
            }

            for (String synonym : synonymService.getSynonyms(word)) {

                if (document.contains(synonym.toLowerCase())) {
                    matches++;
                }

            }

        }

        if (words.length == 0) {
            return 0;
        }

        int score = (matches * 100) / (words.length * 2);

        return Math.min(score, 100);
    }

    private String summarize(
            String content
    ) {

        if (content.length() <= 180) {
            return content;
        }

        return content.substring(0, 180) + "...";
    }
}