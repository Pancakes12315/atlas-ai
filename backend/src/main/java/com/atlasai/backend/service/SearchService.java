package com.atlasai.backend.service;


import com.atlasai.backend.model.SearchResponse;
import com.atlasai.backend.model.Source;
import com.atlasai.backend.model.RankedDocument;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



@Service
public class SearchService {


    private final KnowledgeBaseService knowledgeBaseService;



    public SearchService(
            KnowledgeBaseService knowledgeBaseService
    ) {

        this.knowledgeBaseService = knowledgeBaseService;

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
                        .filter(document ->
                                document.getScore() > 0
                        )
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


            RankedDocument top =
                    rankedDocuments.get(0);



            answer =
                    "Atlas AI found relevant information from "
                    + top.getName()
                    + ". "
                    + summarize(top.getContent());



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
                query.toLowerCase()
                        .split("\\s+");



        String document =
                content.toLowerCase();



        int matches = 0;



        for(String word : words) {


            if(document.contains(word)) {

                matches++;

            }

        }



        if(words.length == 0) {

            return 0;

        }



        return (matches * 100) / words.length;


    }





    private String summarize(
            String content
    ) {


        if(content.length() <= 180) {

            return content;

        }


        return content.substring(0,180)
                + "...";


    }


}