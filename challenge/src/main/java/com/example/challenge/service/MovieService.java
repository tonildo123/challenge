package com.example.challenge.service;

import com.example.challenge.model.CharacterEntity;
import com.example.challenge.model.Gender;
import com.example.challenge.model.Movie;
import com.example.challenge.model.User;
import com.example.challenge.repository.ICharacterRepository;
import com.example.challenge.repository.IGenderRepository;
import com.example.challenge.repository.IMovieRepository;
import com.example.challenge.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private ICharacterRepository iCharacterRepository;

    @Autowired
    private IGenderRepository iGenderRepository;

    @Autowired
    private IUserRepository iUserRepository;

    public Movie createMovie(Movie movie, Long id) {

        User user = iUserRepository.findById(id).get();
        movie.setUser(user);

        return movieRepository.save(movie);
    }
    // traer todos
    public List<Movie> getAllMovies(){
        return  movieRepository.findAll();
    }
    // traer uno por su id
    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("movie not found with id: " + id));
    }

    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("movie not found with id: " + id));

        return movieRepository.save(existingMovie);
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }

    public Movie addGenderAndCharacterToMovie (Long movie_id, Long gender_id, Long character_id){

        Movie movie = movieRepository.findById(movie_id).get();
        Gender gender = iGenderRepository.findById(gender_id).get();
        CharacterEntity characterEntity = iCharacterRepository.findById(character_id).get();

        movie.getGenders().add(gender);
        movie.getCharacterEntities().add(characterEntity);

        return movieRepository.save(movie);

    }

}
