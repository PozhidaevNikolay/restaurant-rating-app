package org.example.ratingapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Feedback {
    private Long id;
    private Long visitorId;
    private Long restaurantId;
    private int rating; // score -> rating
    private String comment; // text -> comment

    public Feedback(Long visitorId, Long restaurantId, int rating, String comment) {
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
    }
}

