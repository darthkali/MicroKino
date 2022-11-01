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
    val cinemaRepository: CinemaRepository? = null
    val locationRepository: LocationRepository? = null


    @GetMapping("/cinemaService/cinemas")
    fun getAllCinemas(): List<Cinema>? {
        return cinemaRepository?.findAll()?.toList()
    }

    @GetMapping("/cinemaService/cinema/{cinemaId}")
    fun getCinemaById(@PathVariable(value = "cinemaId") cinemaId: Long): Cinema? {
        return cinemaRepository?.findCinemaById(cinemaId)
    }


    @GetMapping("/cinemaService/locations")
    fun getAllLocations(): List<Location>? {
        return locationRepository?.findAll()?.toList()
    }

    @GetMapping("/cinemaService/location/{locationId}")
    fun getLocationById(@PathVariable(value = "locationId") locationId: Long): Location? {
        return locationRepository?.findLocationById(locationId)
    }


    @GetMapping("/cinemaService/cinemaHalls")
    fun getAllCinemasHalls(): List<CinemaHall>? {
        return cinemaHallRepository?.findAll()?.toList()
    }

    @GetMapping("/cinemaService/cinemaHall/{cinemaHallId}")
    fun getCinemaHallById(@PathVariable(value = "cinemaHallId") cinemaHallId: Long): CinemaHall? {
        return cinemaHallRepository?.findCinemaHallById(cinemaHallId)
    }



}