package de.keksbendiger.moviesservice


import com.fasterxml.jackson.core.JsonProcessingException
import de.keksbendiger.moviesservice.config.MovieServiceConfig
import de.keksbendiger.moviesservice.models.Movie
import de.keksbendiger.moviesservice.models.Properties
import de.keksbendiger.moviesservice.models.Screening
import de.keksbendiger.moviesservice.repositories.MovieRepository
import de.keksbendiger.moviesservice.repositories.ScreeningRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;









//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class MovieController {
    @Autowired
    val screeningRepository: ScreeningRepository? = null

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

    @GetMapping("/screenings/byMovie/{movieId}")
    fun getScreenings(@PathVariable(value = "movieId") movieId: Long): List<Screening>? {
        return screeningRepository?.findScreeningsByMovieId(movieId)?.toList()
    }

    @PostMapping("/screenings/add/{movieId}")
    fun addScreening(@RequestBody screening: Screening, @PathVariable(value = "movieId") movieId: Long) {
        val movie = movieRepository?.findById(movieId)
        screening.movie = movie?.get()
        screeningRepository?.save(screening);
    }

    @PostMapping("/movies/add")
    fun addMovie(@RequestBody movie: Movie) {
        movieRepository?.save(movie);
    }

    @PostMapping("/screenings/remove")
    fun removeScreening(@RequestBody screening: Screening) {
        screeningRepository?.delete(screening);
    }

    @PostMapping("/movies/remove")
    fun removeMovie(@RequestBody movie: Movie) {
        movieRepository?.delete(movie);
    }


}