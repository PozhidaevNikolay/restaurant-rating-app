package org.example.ratingapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "visitors")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    private int age;

    private char gender;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Feedback> feedbacks = new ArrayList<>();


    public Visitor(String nickname, int age, char gender) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }
}