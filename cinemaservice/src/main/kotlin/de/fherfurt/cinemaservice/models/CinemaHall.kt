package de.fherfurt.cinemaservice.models

import java.time.LocalDate
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "cinemaHall")
data class CinemaHall(
    @Id
    @Column(name = "cinema_hall_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),

    @Column(name = "hall_number")
    val hallNumber: Int = 0,

    @Column
    val seats: Int = 0,
)