package com.pluralsight.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
    //Define a constant for the base URL of the Pluralsight API. Include a placeholder %s for the authorId.
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/sander-mak/all-content";

    //Declare a private static final field for the HttpClient instance.
    //Initialize it using the default factory method.
    private static final HttpClient CLIENT = HttpClient.newHttpClient();


    //Implement method, pass in authorId
    public String getCourserFor(String authorId) {

        //Construct the HTTP Request
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();

        //Handle Exceptions
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); //return raw JSON string, return "" for now.
        } catch (IOException | InterruptedException e) { //Java can catch multi exception
            throw new RuntimeException("Could not call Pluralsight API", e);
        }
    }
}

