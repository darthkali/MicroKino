package de.fherfurt.movieservice.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import de.fherfurt.movieservice.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component

@Component
class MovieResult {
    @Autowired
    val movieRepository: MovieRepository? = null

    @KafkaListener(topics = ["\${kafka.reuest.topic}"], groupId = "\${kafka.group.id}")
    @SendTo
    fun handle(movieId: Long): String? {
        val movie = movieRepository?.findMovieById(movieId)

        val mapper = ObjectMapper()
        return mapper.writeValueAsString(movie)
    }
}