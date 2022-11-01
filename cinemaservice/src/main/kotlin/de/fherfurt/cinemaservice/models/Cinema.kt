package de.fherfurt.cinemaservice.models

import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  cinemaservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "cinemas")
data class Cinema(
    @Id
    @Column(name = "cinema_id")
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