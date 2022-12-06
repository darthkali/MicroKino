package de.fherfurt.showservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

class Movie (
    // TODO potentially use UUIDs here
    val id: Long,

    @JsonIgnore
    var createDt: LocalDate = LocalDate.now(),

    val name: String,

    // in minutes
    val playLength: Int = 90,

    // cast
    val casting: List<String>, //TODO Wurde von "cast" in "casting" umbenannt, da es sonst zu Problemen mit den SQL führt

    val conclusion: String

    // ratings
)