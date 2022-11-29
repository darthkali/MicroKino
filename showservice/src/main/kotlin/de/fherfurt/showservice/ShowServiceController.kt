package de.fherfurt.showservice

import de.fherfurt.showservice.config.ShowServiceConfig
import de.fherfurt.showservice.models.Movie
import de.fherfurt.showservice.models.Show
import de.fherfurt.showservice.repositories.ShowRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  showservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class ShowServiceController {
    @Autowired
    val showRepository: ShowRepository? = null

    @Autowired
    var showServiceConfig: ShowServiceConfig? = null

    private val LOG: Logger = LoggerFactory.getLogger(ShowServiceApplication::class.java)

    @GetMapping("/show/list")
    fun getAllMovies(): List<Show>? {
        return showRepository?.findAll()?.toList()
    }

    @GetMapping("/show/byMovie/{movieId}")
    fun getShows(@PathVariable(value = "movieId") movieId: Long): List<Show>? {
        return showRepository?.findShowsByMovieId(movieId)?.toList()
    }

    @GetMapping("/show/{showId}")
    fun getShowById(@PathVariable(value = "showId") showId: Long): Show? {
        return showRepository?.findShowById(showId)
    }

    @PostMapping("/show/add/{movieId}")
    fun addShow(@RequestBody show: Show, @PathVariable(value = "movieId") movieId: Long) {
        //val movie = movieRepository?.findById(movieId)
        //show.show = movie?.get()
        showRepository?.save(show);
    }

    @PostMapping("/show/remove")
    fun removeShow(@RequestBody show: Show) {
        showRepository?.delete(show);
    }

    @KafkaListener(id = "movie", topics = ["movie-show"], groupId = "movie")
    fun onEvent(movie: Movie): Movie {
        LOG.info("Received: {}", movie)
        return movie
    }
}