package de.fherfurt.cinemaservice.repositories

import de.fherfurt.cinemaservice.models.Location
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : CrudRepository<Location, Long> {
    fun findLocationById(cinemaId: Long): Location
}