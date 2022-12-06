package de.fherfurt.showservice.models

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
    val id: Long,

    @Column(name = "create_dt")
    val createDt: LocalDate? = LocalDate.now(),

    val showDate: LocalDateTime,

    val theatre: String,

    val movieId: Long,

    val location: String,
)