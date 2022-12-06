package de.fherfurt.movieservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  movieservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "movie")
class Movie (
        @Id
        @Column(name = "movie_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // TODO potentially use UUIDs here
        val id: Long,

        @JsonIgnore
        @Column(name = "create_dt")
        var createDt: LocalDate = LocalDate.now(),

        val name: String,

        // in minutes
        val playLength: Int = 90,

        // cast
        @ElementCollection
        val casting: List<String> = listOf(), //TODO Wurde von "cast" in "casting" umbenannt, da es sonst zu Problemen mit den SQL führt


        // reviews
        @ElementCollection
        val reviews: List<String> = listOf(),

        @Column(columnDefinition = "TEXT")
        val conclusion: String = "a default movie conclusion"
//
//        // ratings
)