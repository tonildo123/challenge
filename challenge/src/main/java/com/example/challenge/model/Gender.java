package com.example.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;

    //movie asociada
    // Asociación con películas (un género puede estar en varias películas)
    @ManyToMany(mappedBy = "gender")
    private Set<Movie> movies = new HashSet<>();
}
