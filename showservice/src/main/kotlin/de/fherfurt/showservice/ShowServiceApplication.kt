package de.fherfurt.showservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@FeignClient
class ShowServiceApplication

fun main(args: Array<String>) {
	runApplication<ShowServiceApplication>(*args)
}
