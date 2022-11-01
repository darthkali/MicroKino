package de.keksbendiger.moviesservice.models

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

    /*@Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),*/

    @Column
    val name: String = "",

    /*@Column
    private val baseprice: Double = 5.0,

    @Column
    // in minutes
    private val playLength: Int = 90,*/

    // cast
    // reviews
    // conclusion
    // ratings
)