package de.fherfurt.movieservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieserviceApplication

fun main(args: Array<String>) {
    runApplication<MovieserviceApplication>(*args)
}
