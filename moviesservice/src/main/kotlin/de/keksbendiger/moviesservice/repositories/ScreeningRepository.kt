package de.keksbendiger.moviesservice.repositories

import de.keksbendiger.moviesservice.models.Movie
import de.keksbendiger.moviesservice.models.Screening
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@Repository
interface ScreeningRepository : CrudRepository<Screening, Long> {
    fun findScreeningsByMovie(movie : Movie) : MutableIterable<Screening>
    fun findScreeningsByMovieId(movieId : Long) : MutableIterable<Screening>
}