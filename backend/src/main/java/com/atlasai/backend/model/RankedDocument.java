package com.atlasai.backend.model;


public class RankedDocument {

    private final String name;
    private final String content;
    private final int score;


    public RankedDocument(
            String name,
            String content,
            int score
    ) {

        this.name = name;
        this.content = content;
        this.score = score;

    }


    public String getName() {
        return name;
    }


    public String getContent() {
        return content;
    }


    public int getScore() {
        return score;
    }

}