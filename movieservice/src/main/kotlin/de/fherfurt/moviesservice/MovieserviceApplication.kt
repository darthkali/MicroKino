package de.fherfurt.movieservice

import de.fherfurt.moviesservice.models.Show
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener


@SpringBootApplication
@EnableKafka
class MovieserviceApplication {
    private val LOG: Logger = LoggerFactory.getLogger(MovieserviceApplication::class.java)

    fun main(args: Array<String>) {
        runApplication<MovieserviceApplication>(*args)
    }

    @KafkaListener(id = "show", topics = ["show"], groupId = "movie")
    fun onEvent(show: Show) {
        LOG.info("Received: {}", show)

//        orderManageService.reserve(o)
    }


}