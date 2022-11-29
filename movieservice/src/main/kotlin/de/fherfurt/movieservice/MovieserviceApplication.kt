package de.fherfurt.movieservice

import de.fherfurt.movieservice.models.Movie
import de.fherfurt.movieservice.repositories.MovieRepository
import de.fherfurt.movieservice.models.Show
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate


@SpringBootApplication
@EnableKafka
class MovieserviceApplication {

    @Autowired
    val movieRepository: MovieRepository? = null

    private val LOG: Logger = LoggerFactory.getLogger(MovieserviceApplication::class.java)

    fun main(args: Array<String>) {
        runApplication<MovieserviceApplication>(*args)
    }

    @KafkaListener(id = "show", topics = ["show"], groupId = "movie")
    fun onEvent(show: Show) {
        LOG.info("Received: {}", show)

        val movie: Movie? = movieRepository?.findMovieById(show.movieId)

        val template: KafkaTemplate<Long, Movie>? = null
        template?.send("movie-show", show.movieId, movie)
    }


}