package de.fherfurt.movieservice


import de.fherfurt.movieservice.models.Movie
import de.fherfurt.movieservice.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  movieservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class MovieServiceController {

    @Autowired
    val movieRepository: MovieRepository? = null


    @GetMapping("/movie/list")
    fun getAllMovies(): List<Movie>? {
        return movieRepository?.findAll()?.toList()
    }

    @GetMapping("/movie/{movieId}")
    fun getMovieById(@PathVariable(value = "movieId") movieId: Long): Movie? {
        return movieRepository?.findMovieById(movieId)
    }

    @PostMapping("/movie/add")
    fun addMovie(@RequestBody movie: Movie) {
        movieRepository?.save(movie);
    }

    @PostMapping("/movie/remove")
    fun removeMovie(@RequestBody movie: Movie) {
        movieRepository?.delete(movie);
    }
}