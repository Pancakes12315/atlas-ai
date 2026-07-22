package com.atlasai.backend.model;


public class Source {


    private String name;

    private String section;

    private String updated;

    private int relevance;



    public Source(
            String name,
            String section,
            String updated,
            int relevance
    ) {

        this.name = name;

        this.section = section;

        this.updated = updated;

        this.relevance = relevance;

    }



    public String getName() {

        return name;

    }



    public String getSection() {

        return section;

    }



    public String getUpdated() {

        return updated;

    }



    public int getRelevance() {

        return relevance;

    }


}