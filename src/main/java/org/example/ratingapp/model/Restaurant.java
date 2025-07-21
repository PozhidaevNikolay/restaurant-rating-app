package org.example.ratingapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String summary;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    private Integer avgBill;

    @Column(name = "rating_score", precision = 3, scale = 1)
    private BigDecimal score;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Feedback> feedbacks = new ArrayList<>();

    public Restaurant(String title, String summary, Cuisine cuisine, Integer avgBill) {
        this.title = title;
        this.summary = summary;
        this.cuisine = cuisine;
        this.avgBill = avgBill;
        this.score = BigDecimal.ZERO;
    }
}