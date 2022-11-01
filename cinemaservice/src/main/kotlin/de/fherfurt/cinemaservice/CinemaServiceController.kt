package de.fherfurt.cinemaservice


import de.fherfurt.cinemaservice.models.Cinema
import de.fherfurt.cinemaservice.models.CinemaHall
import de.fherfurt.cinemaservice.models.Location
import de.fherfurt.cinemaservice.repositories.CinemaHallRepository
import de.fherfurt.cinemaservice.repositories.CinemaRepository
import de.fherfurt.cinemaservice.repositories.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
class CinemaServiceController {
    @Autowired
    val cinemaHallRepository: CinemaHallRepository? = null
    @Autowired
    val cinemaRepository: CinemaRepository? = null
    @Autowired
    val locationRepository: LocationRepository? = null


    @GetMapping("/cinemas")
    fun getAllCinemas(): List<Cinema>? {
        return cinemaRepository?.findAll()?.toList()
    }

    @GetMapping("/cinema/{cinemaId}")
    fun getCinemaById(@PathVariable(value = "cinemaId") cinemaId: Long): Cinema? {
        return cinemaRepository?.findCinemaById(cinemaId)
    }


    @GetMapping("/locations")
    fun getAllLocations(): List<Location>? {
        return locationRepository?.findAll()?.toList()
    }

    @GetMapping("/location/{locationId}")
    fun getLocationById(@PathVariable(value = "locationId") locationId: Long): Location? {
        return locationRepository?.findLocationById(locationId)
    }


    @GetMapping("/cinemaHalls")
    fun getAllCinemasHalls(): List<CinemaHall>? {
        return cinemaHallRepository?.findAll()?.toList()
    }

    @GetMapping("/cinemaHall/{cinemaHallId}")
    fun getCinemaHallById(@PathVariable(value = "cinemaHallId") cinemaHallId: Long): CinemaHall? {
        return cinemaHallRepository?.findCinemaHallById(cinemaHallId)
    }


}