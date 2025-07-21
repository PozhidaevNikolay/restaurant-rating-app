package org.example.ratingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.ratingapp.model.Cuisine;
import java.math.BigDecimal;

public class RestaurantDtos {

    // DTO для создания нового ресторана
    public record NewRestaurant(
            @JsonProperty("название") String title,
            @JsonProperty("описание") String summary,
            @JsonProperty("кухня") Cuisine cuisine,
            @JsonProperty("средний_чек") Integer avgBill
    ) {}

    // DTO для отображения ресторана
    public record RestaurantView(
            @JsonProperty("id") Long id,
            @JsonProperty("название") String title,
            @JsonProperty("описание") String summary,
            @JsonProperty("кухня") Cuisine cuisine,
            @JsonProperty("средний_чек") Integer avgBill,
            @JsonProperty("рейтинг") BigDecimal score
    ) {}
}