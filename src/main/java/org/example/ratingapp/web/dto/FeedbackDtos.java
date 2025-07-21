package org.example.ratingapp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDtos {
    public record NewFeedback(
            @JsonProperty("id_посетителя") Long visitorId,
            @JsonProperty("id_ресторана") Long restaurantId,
            @JsonProperty("оценка") int rating,
            @JsonProperty("комментарий") String comment
    ) {}

    public record FeedbackView(
            @JsonProperty("id") Long id,
            @JsonProperty("id_посетителя") Long visitorId,
            @JsonProperty("id_ресторана") Long restaurantId,
            @JsonProperty("оценка") int rating,
            @JsonProperty("комментарий") String comment
    ) {}
}