package de.keksbendiger.moviesservice.models

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "screenings")
data class Screening (
    @Id
    @Column(name = "screening_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    // TODO potentially use UUIDs here
    val id: Long = 0,

    @Column(name = "create_dt")
    val createDt: LocalDate? = LocalDate.now(),

    @Column(name = "showtime")
    val showtime: LocalDateTime? = null,

    @Column
    val price: Double = 5.0,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "movie_id", nullable = true)
    var movie : Movie? = null

)