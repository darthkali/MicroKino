package de.fherfurt.movieservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.FeignClient

@SpringBootApplication
@FeignClient
class MovieserviceApplication

fun main(args: Array<String>) {
	runApplication<MovieserviceApplication>(*args)
}
