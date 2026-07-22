package com.atlasai.backend.model;

import java.util.List;

public class SearchResponse {

    private String answer;

    private int confidence;

    private List<Source> sources;

    private List<String> reasoning;

    private List<String> recommendations;


    public SearchResponse(

            String answer,

            int confidence,

            List<Source> sources,

            List<String> reasoning,

            List<String> recommendations

    ) {

        this.answer = answer;

        this.confidence = confidence;

        this.sources = sources;

        this.reasoning = reasoning;

        this.recommendations = recommendations;

    }


    public String getAnswer() {

        return answer;

    }


    public int getConfidence() {

        return confidence;

    }


    public List<Source> getSources() {

        return sources;

    }


    public List<String> getReasoning() {

        return reasoning;

    }


    public List<String> getRecommendations() {

        return recommendations;

    }

}