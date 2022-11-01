package de.fherfurt.cinemaservice.repositories

import de.fherfurt.cinemaservice.models.Cinema
import de.fherfurt.cinemaservice.models.Screening
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ScreeningRepository : CrudRepository<Screening, Long> {
    fun findScreeningsByCinema(cinema : Cinema) : MutableIterable<Screening>
    fun findScreeningsByCinemaId(cinemaId : Long) : MutableIterable<Screening>
}