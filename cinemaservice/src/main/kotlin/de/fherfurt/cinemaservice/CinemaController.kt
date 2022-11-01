package de.fherfurt.cinemaservice


import de.fherfurt.cinemaservice.config.CinemaServiceConfig
import de.fherfurt.cinemaservice.models.Cinema
import de.fherfurt.cinemaservice.models.Screening
import de.fherfurt.cinemaservice.repositories.CinemaRepository
import de.fherfurt.cinemaservice.repositories.ScreeningRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping


@RestController
class CinemaController {
    @Autowired
    val screeningRepository: ScreeningRepository? = null

    @Autowired
    val cinemaRepository: CinemaRepository? = null

    @Autowired
    var cinemasConfig: CinemaServiceConfig? = null
    
    @GetMapping("/cinemas/list")
    fun getAllCinemas(): List<Cinema>? {
        return cinemaRepository?.findAll()?.toList()
    }

    @GetMapping("/cinemas/{cinemaId}")
    fun getCinemaById(@PathVariable(value = "cinemaId") cinemaId: Long): Cinema? {
        return cinemaRepository?.findCinemaById(cinemaId)
    }

    @GetMapping("/screenings/byCinema/{cinemaId}")
    fun getScreenings(@PathVariable(value = "cinemaId") cinemaId: Long): List<Screening>? {
        return screeningRepository?.findScreeningsByCinemaId(cinemaId)?.toList()
    }

    @PostMapping("/screenings/add/{cinemaId}")
    fun addScreening(@RequestBody screening: Screening, @PathVariable(value = "cinemaId") cinemaId: Long) {
        val cinema = cinemaRepository?.findById(cinemaId)
        screening.cinema = cinema?.get()
        screeningRepository?.save(screening);
    }

    @PostMapping("/cinemas/add")
    fun addCinema(@RequestBody cinema: Cinema) {
        cinemaRepository?.save(cinema);
    }

    @PostMapping("/screenings/remove")
    fun removeScreening(@RequestBody screening: Screening) {
        screeningRepository?.delete(screening);
    }

    @PostMapping("/cinemas/remove")
    fun removeCinema(@RequestBody cinema: Cinema) {
        cinemaRepository?.delete(cinema);
    }


}