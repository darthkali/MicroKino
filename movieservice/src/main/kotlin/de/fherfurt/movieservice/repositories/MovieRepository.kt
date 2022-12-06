package de.fherfurt.movieservice.repositories

import de.fherfurt.movieservice.models.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  movieservice
// created:  04.10.2022
//--------------------------------------------------//
@Repository
interface MovieRepository : CrudRepository<Movie, Long> {
    fun findMovieById(movieId: Long): Movie
}