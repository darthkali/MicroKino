package de.fherfurt.movieservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Math.random

@SpringBootTest
class MovieserviceApplicationTests {

    @Test
    fun contextLoads() {
        val randomNumber = random() * 10
        println("RandomNumber : $randomNumber")
        assert(randomNumber > 5)
    }

}
