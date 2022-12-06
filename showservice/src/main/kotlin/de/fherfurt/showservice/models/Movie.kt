package de.fherfurt.showservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate

class Movie(
    val id: Long,
    @JsonIgnore
    var createDt: LocalDate = LocalDate.now(),
    val name: String,
    val playLength: Int = 90,
    val casting: List<String>,
    val conclusion: String
)