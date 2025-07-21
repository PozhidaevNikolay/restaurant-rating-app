package org.example.ratingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Cuisine {
    @JsonProperty("Европейская")
    EUROPEAN,
    @JsonProperty("Итальянская")
    ITALIAN,
    @JsonProperty("Азиатская")
    ASIAN,
    @JsonProperty("Американская")
    AMERICAN,
    @JsonProperty("Грузинская")
    GEORGIAN
}