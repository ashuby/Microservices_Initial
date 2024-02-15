package org.example.Controller;

import org.example.Model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("movie-service/movie")
public interface MovieRestConsumer {
    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie);

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies();

}
