package org.example.ratingapp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VisitorDtos {
    public record NewVisitor(
            @JsonProperty("имя") String nickname,
            @JsonProperty("возраст") int age,
            @JsonProperty("пол") char gender
    ) {}

    public record VisitorView(
            @JsonProperty("id") Long id,
            @JsonProperty("имя") String nickname,
            @JsonProperty("возраст") int age,
            @JsonProperty("пол") char gender
    ) {}
}