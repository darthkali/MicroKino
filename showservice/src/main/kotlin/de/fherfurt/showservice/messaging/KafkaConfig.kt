package de.fherfurt.showservice.messaging

import de.fherfurt.showservice.models.Movie
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate

@Configuration
class KafkaConfig {
    @Value("\${kafka.group.id}")
    private val groupId: String? = null

    @Value("\${kafka.reply.topic}")
    private val replyTopic: String? = null

    @Bean
    fun replyingKafkaTemplate(
        producerFactory: ProducerFactory<String?, Long>?,
        factory: ConcurrentKafkaListenerContainerFactory<String?, Movie?>
    ): ReplyingKafkaTemplate<String?, Long, Movie?> {
        val replyContainer: ConcurrentMessageListenerContainer<String?, Movie?> = factory.createContainer(replyTopic)
        replyContainer.containerProperties.isMissingTopicsFatal = false
        replyContainer.containerProperties.setGroupId(groupId!!)
        return ReplyingKafkaTemplate(producerFactory, replyContainer)
    }

    @Bean
    fun replyTemplate(
        producerFactory: ProducerFactory<String, Movie>?,
        factory: ConcurrentKafkaListenerContainerFactory<String?, Movie?>
    ): KafkaTemplate<String, Movie> {
        val kafkaTemplate: KafkaTemplate<String, Movie> = KafkaTemplate(producerFactory!!)
        factory.containerProperties.isMissingTopicsFatal = false
        factory.setReplyTemplate(kafkaTemplate)
        return kafkaTemplate
    }
}