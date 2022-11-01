package de.fherfurt.cinemaservice.config

import org.springframework.boot.context.properties.ConfigurationProperties


@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "cinemaservice")
class CinemaServiceConfig {
     val msg: String? = null
     val buildVersion: String? = null
     val mailDetails: Map<String, String>? = null
     val activeBranches: List<String>? = null
}