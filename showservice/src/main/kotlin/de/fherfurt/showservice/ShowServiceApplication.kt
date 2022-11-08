package de.fherfurt.showservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.FeignClient

@SpringBootApplication
//@FeignClient
class ShowServiceApplication

fun main(args: Array<String>) {
	runApplication<ShowServiceApplication>(*args)
}
