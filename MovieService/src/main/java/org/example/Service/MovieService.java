package org.example.Service;

import org.bson.types.ObjectId;
import org.example.Model.Movie;
import org.example.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepo;

    public MovieService(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public String addMovie(Movie movie){
        Movie savedMovie=movieRepo.save(movie);
        return "{" +
                "\"message\":"+"Successfully added Movie Details\",\n"+
                "\"data\":"+savedMovie+",\n"+
                "}";
    }
    public Movie findMovieById(ObjectId id){
        Optional<Movie> o=movieRepo.findById(id);
        if(o.isEmpty()) return null;
        return o.get();
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }
    public void deleteMovie(Movie movie) {
        movieRepo.delete(movie);
    }
    public String updateMovie(String id, Movie movie) {
        ObjectId foundObj = new ObjectId(id);
        Optional<Movie> optionalMovie = movieRepo.findById(foundObj);
        if(optionalMovie.isEmpty()) throw new RuntimeException("Given movie doesn't exist");
        Movie foundMovie = optionalMovie.get();
        if(movie.getMovieName()!=null) {
            foundMovie.setMovieName(movie.getMovieName());
        }
        if(movie.getMovieDes()!=null) {
            foundMovie.setMovieDes(movie.getMovieDes());
        }
        if(movie.getMovieReview()!=null) {
            foundMovie.setMovieReview(movie.getMovieReview());
        }
        if(movie.getMovieReleaseDate()!=null) {
            foundMovie.setMovieReleaseDate(movie.getMovieReleaseDate());
        }
        Movie resultMovie = movieRepo.save(foundMovie);
        return "{" +
                "\"message\":"+"Successfully updated Movie Details\",\n"+
                "\"data\":"+resultMovie+",\n"+
                "}";
    }
    public boolean existbyid(ObjectId id){
        return movieRepo.existsById(id);
    }
}
