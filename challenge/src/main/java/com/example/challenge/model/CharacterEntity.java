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
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private int age;
    private int weight;
    private String story;

    // movie  asociadas
    // Asociación con películas (un personaje puede estar en varias películas)
    @ManyToMany(mappedBy = "character") // para que se relacione a cursos
    private Set<Movie> movie = new HashSet<>();



}
