package com.atlasai.backend.model;

public class DocumentChunk {

    private final String documentName;
    private final String text;
    private final int score;

    public DocumentChunk(
            String documentName,
            String text,
            int score
    ) {
        this.documentName = documentName;
        this.text = text;
        this.score = score;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }

}