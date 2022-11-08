package de.fherfurt.moviesservice.repositories

import de.fherfurt.moviesservice.models.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@Repository
interface MovieRepository : CrudRepository<Movie, Long> {
    fun findMovieById(movieId : Long) : Movie
}