package de.fherfurt.moviesservice.models

import java.time.LocalDate
import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "movies")
data class Movie(
        @Id
        @Column(name = "movie_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        // TODO potentially use UUIDs here
        val id: Long = 0,

        @Column(name = "create_dt")
        private val createDt: LocalDate? = LocalDate.now(),

        @Column
        val name: String,

        @Column
        // in minutes
        val playLength: Int = 90,

        // cast
        @ElementCollection
        val cast: List<String>,

        // reviews
        @ElementCollection
        val reviews: List<String>,

        // conclusion
        @Column
        val conclusion: String,

        // ratings
)