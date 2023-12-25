package com.example.challenge.service;

import com.example.challenge.model.Movie;
import com.example.challenge.model.Profile;
import com.example.challenge.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;

    public Movie createMovie(Movie movie, Long id) {

        Optional<Movie> optionalMovie = movieRepository.findMovieByUserIdAndTitle(id, movie.getTitle());

        if (optionalMovie.isPresent()) {
            return null;
        }else {
            movie.setUserId(id);
            return movieRepository.save(movie);
        }

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

}
