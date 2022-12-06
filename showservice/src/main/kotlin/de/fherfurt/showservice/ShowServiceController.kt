package de.fherfurt.showservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import de.fherfurt.showservice.models.Movie
import de.fherfurt.showservice.models.Show
import de.fherfurt.showservice.repositories.ShowRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.requestreply.RequestReplyFuture
import org.springframework.web.bind.annotation.*

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
    private val replyingKafkaTemplate: ReplyingKafkaTemplate<String?, Long, Movie?>? = null

    @Value("\${kafka.reuest.topic}")
    private val requestTopic: String? = null


    @GetMapping("/show/list")
    fun getAllMovies(): List<Show>? {
        return showRepository?.findAll()?.toList()
    }

    @GetMapping("/show/byMovie/{movieId}")
    fun getShows(@PathVariable(value = "movieId") movieId: Long): List<Show>? {
        return showRepository?.findShowsByMovieId(movieId)?.toList()
    }

    @GetMapping("/show/details/{showId}")
    fun getObject(@PathVariable(value = "showId") showId: Long): ResponseEntity<String?>? {
        val show = showRepository?.findShowById(showId)
        val movieId = show?.movieId

        val record: ProducerRecord<String?, Long> =
            ProducerRecord(requestTopic, 0, show?.id.toString(), movieId) // key, value could be show and movieId
        val future: RequestReplyFuture<String?, Long, Movie?> = replyingKafkaTemplate!!.sendAndReceive(record)
        val response: ConsumerRecord<String?, Movie?>? = future.get()

        val mapper = ObjectMapper().registerKotlinModule()
        val movie = mapper.readValue<Movie?>(response!!.value().toString())

        return ResponseEntity<String?>(mapper.writeValueAsString(movie) + show, HttpStatus.OK)
    }

    @PostMapping("/show/add/{movieId}")
    fun addShow(@RequestBody show: Show, @PathVariable(value = "movieId") movieId: Long) {
        showRepository?.save(show);
    }

    @PostMapping("/show/remove")
    fun removeShow(@RequestBody show: Show) {
        showRepository?.delete(show);
    }

    @GetMapping("/show/id/{showId}")
    fun getShowById(@PathVariable(value = "showId") showId: Long): Show? {
        return showRepository?.findShowById(showId)
    }
}