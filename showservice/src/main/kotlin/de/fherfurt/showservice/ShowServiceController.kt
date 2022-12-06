package de.fherfurt.showservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.fherfurt.showservice.config.ShowServiceConfig
import de.fherfurt.showservice.models.Movie
import de.fherfurt.showservice.models.Show
import de.fherfurt.showservice.repositories.ShowRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.requestreply.RequestReplyFuture
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ExecutionException


//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  showservice
// created:  04.10.2022
//--------------------------------------------------//
@RestController
class ShowServiceController {
    @Autowired
    val showRepository: ShowRepository? = null

    @Autowired
    var showServiceConfig: ShowServiceConfig? = null

    private val LOG: Logger = LoggerFactory.getLogger(ShowServiceApplication::class.java)

    @GetMapping("/show/list")
    fun getAllMovies(): List<Show>? {
        return showRepository?.findAll()?.toList()
    }


    @GetMapping("/show/byMovie/{movieId}")
    fun getShows(@PathVariable(value = "movieId") movieId: Long): List<Show>? {
        return showRepository?.findShowsByMovieId(movieId)?.toList()
    }


    @Value("\${kafka.reuest.topic}")
    private val requestTopic: String? = null

    @Autowired
    private val replyingKafkaTemplate: ReplyingKafkaTemplate<String?, Long, Movie?>? = null

//    @PostMapping("/show/details")
//    @Throws(InterruptedException::class, ExecutionException::class)
//    fun getObject(@RequestBody show: Show): ResponseEntity<Movie?>? {
//        val record: ProducerRecord<String?, Long> =
//            ProducerRecord(requestTopic, null, show.id.toString(), show.movieId) // key, value could be show and movieId
//        val future: RequestReplyFuture<String?, Long, Movie?> = replyingKafkaTemplate!!.sendAndReceive(record)
//        val response: ConsumerRecord<String?, Movie?>? = future.get()
//        return ResponseEntity<Movie?>(response!!.value(), HttpStatus.OK)
//    }

//    @GetMapping("/show/list")
//    fun getAllMovies(): List<Show>? {
//        return showRepository?.findAll()?.toList()
//    }

    @GetMapping("/show/details")
    fun getObject():ResponseEntity<String?>? {
        val record: ProducerRecord<String?, Long> =
            ProducerRecord(requestTopic, 0, "test", 1L) // key, value could be show and movieId
        val future: RequestReplyFuture<String?, Long, Movie?> = replyingKafkaTemplate!!.sendAndReceive(record)
        val response: ConsumerRecord<String?, Movie?>? = future.get()

        LOG.info("SENDING REQUEST OVER KAFKA")

        val mapper = ObjectMapper()
        val movie = mapper.readValue<Movie?>(response!!.value().toString())

        return ResponseEntity<String?>(mapper.writeValueAsString(movie), HttpStatus.OK)
    }




    @PostMapping("/show/add/{movieId}")
    fun addShow(@RequestBody show: Show, @PathVariable(value = "movieId") movieId: Long) {
        //val movie = movieRepository?.findById(movieId)
        //show.show = movie?.get()
        showRepository?.save(show);
    }

    @PostMapping("/show/remove")
    fun removeShow(@RequestBody show: Show) {
        showRepository?.delete(show);
    }

//    @KafkaListener(id = "movie", topics = ["movie-show"], groupId = "movie")
//    fun onEvent(movie: Movie): Movie {
//        LOG.info("Received: {}", movie)
//        return movie
//    }
//

    @GetMapping("/show/id/{showId}")
    fun getShowById(@PathVariable(value = "showId") showId: Long): Show? {
        return showRepository?.findShowById(showId)
    }



}