package com.atlasai.backend.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SynonymService {

    private final Map<String, List<String>> synonyms = new HashMap<>();

    public SynonymService() {

        synonyms.put(
                "vacation",
                List.of(
                        "leave",
                        "holiday",
                        "pto",
                        "benefits",
                        "time off"
                )
        );

        synonyms.put(
                "customer",
                List.of(
                        "client",
                        "enterprise",
                        "buyer"
                )
        );

        synonyms.put(
                "marketing",
                List.of(
                        "campaign",
                        "promotion",
                        "advertising"
                )
        );

        synonyms.put(
                "sales",
                List.of(
                        "revenue",
                        "pipeline",
                        "business"
                )
        );

    }

    public List<String> getSynonyms(String word) {

        return synonyms.getOrDefault(
                word.toLowerCase(),
                List.of()
        );

    }

}