package de.fherfurt.movieservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  movieservice
// created:  04.10.2022
//--------------------------------------------------//
@Entity
@Table(name = "movie")
class Movie(
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @JsonIgnore
    @Column(name = "create_dt")
    var createDt: LocalDate = LocalDate.now(),

    val name: String,

    // in minutes
    val playLength: Int = 90,

    @ElementCollection(fetch = FetchType.EAGER)
    val casting: List<String> = listOf(),

    @Column(columnDefinition = "TEXT")
    val conclusion: String = "a default movie conclusion"
)