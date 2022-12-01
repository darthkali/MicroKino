package de.fherfurt.movieservice.messaging

import de.fherfurt.movieservice.models.Movie
import de.fherfurt.movieservice.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom

@Component
class MovieResult {

    @Autowired
    val movieRepository: MovieRepository? = null


    @KafkaListener(topics = ["\${kafka.reuest.topic}"], groupId = "\${kafka.group.id}")
    @SendTo
    fun handle(movieId: Long): Movie? {
        println("Calculating Result...")
        val total: Double = ThreadLocalRandom.current().nextDouble(2.5, 9.9)
        return movieRepository?.findMovieById(movieId)
    }
}