package com.example.challenge.controller;

import com.example.challenge.model.Movie;
import com.example.challenge.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("create/{id}")
    @ResponseBody
    public Movie createMovie(@RequestBody  Movie movie,@PathVariable Long id){

        return movieService.createMovie(movie, id);
    }

    @PostMapping("create/{movie_id}/gender/{gender_id}/char/{character_id}")
    public ResponseEntity<?> addGenderAndCharacterToMovie(
            @PathVariable Long movie_id,
            @PathVariable Long gender_id,
            @PathVariable Long character_id) {
        try {
            Movie movie = movieService.addGenderAndCharacterToMovie(movie_id, gender_id, character_id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("No se encontró la película, género o personaje con el ID proporcionado.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll")
    public List<Movie> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<Movie> getOne(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);

        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable Long id) {
        movie.setId(id);
        Movie updatedMovie = movieService.updateMovie(id, movie);
        if (updatedMovie != null) {
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return new ResponseEntity<>("pelicula borrada", HttpStatus.OK);
    }
}
