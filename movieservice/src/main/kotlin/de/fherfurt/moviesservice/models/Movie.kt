package de.fherfurt.movieservice.models

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
        @GeneratedValue(strategy = GenerationType.AUTO)
        // TODO potentially use UUIDs here
        val id: Long,

        @Column(name = "create_dt")
        var createDt: LocalDate = LocalDate.now(),

        val name: String,

        // in minutes
        val playLength: Int = 90,

        // cast
        @ElementCollection
        val cast: List<String>,

        // reviews
        @ElementCollection
        val reviews: List<String>,

        val conclusion: String

        // ratings
)