package de.fherfurt.cinemaservice.repositories

import de.fherfurt.cinemaservice.models.CinemaHall
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CinemaHallRepository : CrudRepository<CinemaHall, Long> {
    fun findCinemaHallById(cinemaId: Long): CinemaHall
}