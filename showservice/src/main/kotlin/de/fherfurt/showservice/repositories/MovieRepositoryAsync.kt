package de.fherfurt.showservice.repositories

import de.fherfurt.showservice.ShowServiceApplication
import de.fherfurt.showservice.models.Movie
import de.fherfurt.showservice.models.Show
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

open class MovieRepositoryAsync {

    private val LOG: Logger = LoggerFactory.getLogger(ShowServiceApplication::class.java)

    @Async
    open fun getMovieDetails(show: Show): CompletableFuture<Movie> {
        return CompletableFuture.completedFuture(stream())
    }

    fun stream(builder: StreamsBuilder): KStream<Long?, Show?>? {
        val showSerde: JsonSerde<Show> = JsonSerde(Show::class.java)
        val stream: KStream<Long?, Show?> =
                builder.stream(
                        "movie-show", Consumed.with(Serdes.Long(), showSerde)
                )

        stream.peek { k, o -> LOG.info("Output: {}", o) }
                .to("show")
        return stream
    }
}