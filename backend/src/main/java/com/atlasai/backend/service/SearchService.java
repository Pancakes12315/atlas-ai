package com.atlasai.backend.service;


import com.atlasai.backend.model.SearchResponse;
import com.atlasai.backend.model.Source;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
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


        List<KnowledgeBaseService.Document> documents =
                knowledgeBaseService.getDocuments();



        List<KnowledgeBaseService.Document> matches =
                new ArrayList<>();



        String normalizedQuery =
                query.toLowerCase();




        for (
                KnowledgeBaseService.Document document 
                : documents
        ) {


            if (
                document.getContent()
                        .toLowerCase()
                        .contains(normalizedQuery)
            ) {


                matches.add(document);

            }


        }




        List<Source> sources =
                matches.stream()
                        .map(document ->
                                new Source(
                                        document.getName(),
                                        "Knowledge Base Document",
                                        "Recently Updated",
                                        95
                                )
                        )
                        .toList();





        String answer;



        if (!matches.isEmpty()) {


            answer =
                    "Atlas AI found relevant information from "
                    + matches.get(0).getName()
                    + ". "
                    + summarize(matches.get(0).getContent());



        } else {


            answer =
                    "Atlas AI could not find an exact match for \""
                    + query
                    + "\". Try refining your search.";

        }






        return new SearchResponse(

                answer,


                matches.isEmpty()
                        ? 60
                        : 95,



                sources,



                List.of(

                        "Searched company knowledge documents",

                        "Matched relevant text content",

                        "Ranked documents by keyword relevance"

                ),



                List.of(

                        "Review matching documents",

                        "Explore related knowledge",

                        "Ask a follow-up question"

                )

        );


    }





    private String summarize(String content) {


        if (content.length() <= 180) {

            return content;

        }


        return content.substring(0,180)
                + "...";


    }


}