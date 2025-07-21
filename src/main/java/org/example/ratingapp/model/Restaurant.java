package org.example.ratingapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String title; // name -> title
    private String summary; // description -> summary
    private Cuisine cuisine;
    private Integer avgBill; // averageCheck -> avgBill (Integer)
    private BigDecimal score; // rating -> score

    public Restaurant(String title, String summary, Cuisine cuisine, Integer avgBill) {
        this.title = title;
        this.summary = summary;
        this.cuisine = cuisine;
        this.avgBill = avgBill;
        this.score = BigDecimal.ZERO;
    }
}