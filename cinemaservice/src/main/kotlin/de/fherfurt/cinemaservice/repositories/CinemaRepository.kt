package de.fherfurt.cinemaservice.repositories

import de.fherfurt.cinemaservice.models.Cinema
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  cinemaservice
// created:  04.10.2022
//--------------------------------------------------//
@Repository
interface CinemaRepository : CrudRepository<Cinema, Long> {
    fun findCinemaById(cinemaId : Long) : Cinema
}