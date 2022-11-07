package de.fherfurt.moviesservice


import de.fherfurt.moviesservice.config.MovieServiceConfig
import de.fherfurt.moviesservice.models.Movie
import de.fherfurt.moviesservice.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class MovieServiceController {

    @Autowired
    val movieRepository: MovieRepository? = null

    @Autowired
    var moviesConfig: MovieServiceConfig? = null


//    //--- Properties
//    @GetMapping("/movies/properties")
//    @kotlin.Throws(JsonProcessingException::class)
//    fun getPropertyDetails(): String? {
//        val ow: ObjectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
//
//        if (moviesConfig != null) {
//            val properties = Properties(moviesConfig!!.msg!!, moviesConfig!!.buildVersion!!, moviesConfig!!.mailDetails!!, moviesConfig!!.activeBranches!!)
//            return ow.writeValueAsString(properties)
//        }
//
//        return null
//    }


    @GetMapping("/movies/list")
    fun getAllMovies(): List<Movie>? {
        return movieRepository?.findAll()?.toList()
    }

    @GetMapping("/movies/{movieId}")
    fun getMovieById(@PathVariable(value = "movieId") movieId: Long): Movie? {
        return movieRepository?.findMovieById(movieId)
    }

    @PostMapping("/movies/add")
    fun addMovie(@RequestBody movie: Movie) {
        movieRepository?.save(movie);
    }

    @PostMapping("/movies/remove")
    fun removeMovie(@RequestBody movie: Movie) {
        movieRepository?.delete(movie);
    }


}