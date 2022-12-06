package de.fherfurt.showservice.repositories

import de.fherfurt.showservice.models.Show
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  showservice
// created:  04.11.2022
//--------------------------------------------------//
@Repository
interface ShowRepository : CrudRepository<Show, Long> {
    fun findShowsByMovieId(movieId: Long): MutableIterable<Show>
    fun findShowById(showId: Long): Show
}