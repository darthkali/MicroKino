package de.fherfurt.showservice

import de.fherfurt.showservice.config.ShowServiceConfig
import de.fherfurt.showservice.models.Show
import de.fherfurt.showservice.repositories.ShowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class ShowServiceController {
    @Autowired
    val showRepository: ShowRepository? = null

    @Autowired
    var showServiceConfig: ShowServiceConfig? = null


    @GetMapping("/shows/list")
    fun getAllMovies(): List<Show>? {
        return showRepository?.findAll()?.toList()
    }

    @GetMapping("/shows/byMovie/{movieId}")
    fun getShows(@PathVariable(value = "movieId") movieId: Long): List<Show>? {
        return showRepository?.findShowsByMovieId(movieId)?.toList()
    }

    @GetMapping("/shows/{showId}")
    fun getShowById(@PathVariable(value = "showId") showId: Long): Show? {
        return showRepository?.findShowById(showId)
    }

    @PostMapping("/shows/add/{movieId}")
    fun addShow(@RequestBody show: Show, @PathVariable(value = "movieId") movieId: Long) {
        //val movie = movieRepository?.findById(movieId)
        //show.show = movie?.get()
        showRepository?.save(show);
    }

    @PostMapping("/shows/remove")
    fun removeShow(@RequestBody show: Show) {
        showRepository?.delete(show);
    }

}