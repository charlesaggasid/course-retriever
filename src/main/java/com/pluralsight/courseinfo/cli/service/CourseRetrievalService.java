package com.pluralsight.courseinfo.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrievalService {
    //Define a constant for the base URL of the Pluralsight API. Include a placeholder %s for the authorId.
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/sander-mak/all-content";

    //Declare a private static final field for the HttpClient instance.
    //Initialize it using the default factory method.
    private static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    //Implement method, pass in authorId
    public List<PluralsightCourse> getCourserFor(String authorId) {

        //Construct the HTTP Request
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();

        //Handle Exceptions
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.statusCode()) {
                case 200 ->toPluralsightCourses(response); // we want to show pluralsight courses
                case 404 -> List.of();
                default -> throw new RuntimeException("Unexpected response code: " + response.statusCode());

            }; //return raw JSON string, return "" for now.
        } catch (IOException | InterruptedException e) { //Java can catch multi exception
            throw new RuntimeException("Could not call Pluralsight API", e);
        }
    }

    private static List<PluralsightCourse> toPluralsightCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = OBJECT_MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, PluralsightCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}

