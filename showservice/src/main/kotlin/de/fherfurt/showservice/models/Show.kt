package de.fherfurt.showservice.models

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  showservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "show")
class Show(
        @Id
        @Column(name = "show_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // TODO potentially use UUIDs here
        val id: Long,

        @Column(name = "create_dt")
        val createDt: LocalDate? = LocalDate.now(),

        val showDate: LocalDateTime,

        // String oder Number?
        val theatre: String,

        val movieId: Long,

        // String oder Number?
        val location: String,
)