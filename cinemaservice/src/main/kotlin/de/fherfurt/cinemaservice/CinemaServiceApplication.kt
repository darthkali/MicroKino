package de.fherfurt.cinemaservice

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CinemaServiceApplication

fun main(args: Array<String>) {
    runApplication<CinemaServiceApplication>(*args)
}
