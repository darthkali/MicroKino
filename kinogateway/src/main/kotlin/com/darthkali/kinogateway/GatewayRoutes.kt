package com.darthkali.kinogateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@EnableEurekaClient
@Configuration
class GatewayRoutes {

    @Bean
    fun myRoutes(builder: RouteLocatorBuilder): RouteLocator? {
        return builder.routes()
            .route { p: PredicateSpec ->
                p
                    .path("/keksbendiger/movieservice/**")
                    .filters { f: GatewayFilterSpec ->
                        f.rewritePath("/keksbendiger/movieservice/(?<segment>.*)", "/\${segment}")
                            .addResponseHeader("X-Response-Time", LocalDate.now().toString())
                    }
                    .uri("lb://MOVIE")
            }.build()
    }

}
