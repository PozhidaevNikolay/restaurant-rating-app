package org.example.ratingapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Visitor {
    private Long id;
    private String nickname; // name -> nickname
    private int age;
    private char gender; // Enum -> char ('M', 'F')

    public Visitor(String nickname, int age, char gender) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }
}