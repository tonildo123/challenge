package com.example.challenge.repository;

import com.example.challenge.model.CharacterEntity;
import com.example.challenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findCharacterByMovieIdAndName(Long movieId, String name);
}
