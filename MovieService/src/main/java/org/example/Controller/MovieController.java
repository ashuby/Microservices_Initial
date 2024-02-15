package org.example.Controller;

import org.bson.types.ObjectId;
import org.example.Model.Movie;
import org.example.Repository.MovieRepository;
import org.example.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @DeleteMapping("/deleteMovie")
    public void deleteMovie(@RequestBody Movie movie) {
        movieService.deleteMovie(movie);
    }

    @PostMapping("/findById/{id}")
    public Movie findMovieById(@PathVariable ObjectId id) {
        return movieService.findMovieById(id);
    }
    @PutMapping("/updatemovie/{id}")
        public String updateMovie(@PathVariable String id,@RequestBody Movie movie) {
        return movieService.updateMovie(id,movie);
    }
}
