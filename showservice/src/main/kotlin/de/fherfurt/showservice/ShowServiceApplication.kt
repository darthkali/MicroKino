package de.fherfurt.showservice

import de.fherfurt.showservice.models.Movie
import de.fherfurt.showservice.models.Show
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.JoinWindows
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.StreamJoined
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerde
import java.time.Duration


@SpringBootApplication
@EnableKafkaStreams
class ShowServiceApplication //{
//    private val LOG: Logger = LoggerFactory.getLogger(ShowServiceApplication::class.java)

    fun main(args: Array<String>) {
        runApplication<ShowServiceApplication>(*args)
    }
//
//
//    @Bean
//    fun showTopic(): NewTopic? {
//        return TopicBuilder.name("show")
//            .compact()
//            .build()
//    }
//
//    @Bean
//    fun movieTopic(): NewTopic? {
//        return TopicBuilder.name("movie-show")
//            .compact()
//            .build()
//    }
//
//    @Bean
//    fun stream(builder: StreamsBuilder): KStream<Long?, Show?>? {
//        val showSerde: JsonSerde<Show> = JsonSerde(Show::class.java)
//        val stream: KStream<Long?, Show?> =
//            builder.stream(
//                "movie-show", Consumed.with(Serdes.Long(), showSerde)
//            )
//
//        stream.peek { k, o -> LOG.info("Output: {}", o) }
//            .to("show")
//        return stream
//    }
//
//
//
//
//}
