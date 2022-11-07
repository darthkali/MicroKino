package de.fherfurt.showservice.models

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  moviesservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "shows")
data class Show(
        @Id
        @Column(name = "show_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        // TODO potentially use UUIDs here
        val id: Long = 0,

        @Column(name = "create_dt")
        val createDt: LocalDate? = LocalDate.now(),

        @Column
        val showDate: LocalDateTime,

        // String oder Number?
        @Column
        val theatre: String,

        // String oder Number?
        @Column
        val location: String,
)