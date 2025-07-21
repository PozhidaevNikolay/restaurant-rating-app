package org.example.ratingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VisitorDtos {

    // DTO для создания нового посетителя (входящие данные)
    public record NewVisitor(
            @JsonProperty("имя") String nickname,
            @JsonProperty("возраст") int age,
            @JsonProperty("пол") char gender
    ) {}

    // DTO для отображения посетителя (исходящие данные)
    public record VisitorView(
            @JsonProperty("id") Long id,
            @JsonProperty("имя") String nickname,
            @JsonProperty("возраст") int age,
            @JsonProperty("пол") char gender
    ) {}
}