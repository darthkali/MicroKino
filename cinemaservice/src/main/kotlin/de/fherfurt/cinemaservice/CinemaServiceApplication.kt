package de.fherfurt.cinemaservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.FeignClient

@SpringBootApplication
@FeignClient
class CinemaServiceApplication

fun main(args: Array<String>) {
	runApplication<CinemaServiceApplication>(*args)
}
