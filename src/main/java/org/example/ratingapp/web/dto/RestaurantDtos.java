package org.example.ratingapp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.ratingapp.model.Cuisine;
import java.math.BigDecimal;

public class RestaurantDtos {
    public record NewRestaurant(
            @JsonProperty("название") String title,
            @JsonProperty("описание") String summary,
            @JsonProperty("кухня") Cuisine cuisine,
            @JsonProperty("средний_чек") Integer avgBill
    ) {}

    public record RestaurantView(
            @JsonProperty("id") Long id,
            @JsonProperty("название") String title,
            @JsonProperty("описание") String summary,
            @JsonProperty("кухня") Cuisine cuisine,
            @JsonProperty("средний_чек") Integer avgBill,
            @JsonProperty("рейтинг") BigDecimal score
    ) {}
}