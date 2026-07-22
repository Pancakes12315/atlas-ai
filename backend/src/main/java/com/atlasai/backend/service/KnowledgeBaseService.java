package com.atlasai.backend.service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;



@Service
public class KnowledgeBaseService {


    private final List<Document> documents = new ArrayList<>();



    public KnowledgeBaseService() {

        loadDocuments();

    }




    private void loadDocuments() {


        try {


            var resource = new ClassPathResource("knowledge");


            var path = resource.getFile().toPath();



            Files.list(path).forEach(file -> {


                try {


                    String content =
                            Files.readString(file);



                    documents.add(
                            new Document(
                                    file.getFileName().toString(),
                                    content
                            )
                    );



                } catch (IOException e) {


                    e.printStackTrace();

                }


            });



            System.out.println(
                    "Loaded documents: " 
                    + documents.size()
            );



        } catch (IOException e) {


            e.printStackTrace();

        }

    }




    public List<Document> getDocuments() {

        return documents;

    }




    public static class Document {


        private final String name;

        private final String content;




        public Document(
                String name,
                String content
        ) {

            this.name = name;

            this.content = content;

        }



        public String getName() {

            return name;

        }




        public String getContent() {

            return content;

        }


    }

}