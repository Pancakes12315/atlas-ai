package com.atlasai.backend.controller;

import com.atlasai.backend.model.SearchResponse;
import com.atlasai.backend.service.SearchService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SearchController {


    private final SearchService searchService;


    public SearchController(SearchService searchService) {

        this.searchService = searchService;

    }



    @GetMapping("/search")
    public SearchResponse search(
            @RequestParam String query
    ) {

        return searchService.search(query);

    }

}