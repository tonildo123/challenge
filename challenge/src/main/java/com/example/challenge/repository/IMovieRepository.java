package com.example.challenge.repository;

import com.example.challenge.model.Movie;
import com.example.challenge.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieByUserIdAndTitle(Long userId, String title);
}
