package de.fherfurt.movieservice.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import de.fherfurt.movieservice.MovieserviceApplication
import de.fherfurt.movieservice.repositories.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component

@Component
class MovieResult {

    @Autowired
    val movieRepository: MovieRepository? = null

    private val LOG: Logger = LoggerFactory.getLogger(MovieserviceApplication::class.java)


    @KafkaListener(topics = ["\${kafka.reuest.topic}"], groupId = "\${kafka.group.id}")
    @SendTo
    //fun handle(movieId: Long): Movie? {
    fun handle(movieId: Long): String? {

        LOG.info("Calculating Result for movieId: " + movieId)


//        val total: Double = ThreadLocalRandom.current().nextDouble(2.5, 9.9)
//        return movieRepository?.findMovieById(movieId)

        val mapper : ObjectMapper = ObjectMapper()
        //return mapper.writeValueAsString(Movie(id = 5, name = "Test"))
        val movie = movieRepository?.findMovieById(movieId)
        LOG.info(movie?.casting.toString())
        return mapper.writeValueAsString(movie)
    }
}