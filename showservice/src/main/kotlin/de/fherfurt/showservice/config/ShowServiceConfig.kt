package de.fherfurt.showservice.config

import org.springframework.boot.context.properties.ConfigurationProperties


@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "showservice")
class ShowServiceConfig {
     val msg: String? = null
     val buildVersion: String? = null
     val mailDetails: Map<String, String>? = null
     val activeBranches: List<String>? = null
}