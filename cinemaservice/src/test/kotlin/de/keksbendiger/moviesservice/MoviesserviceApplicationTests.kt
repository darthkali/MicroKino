package de.keksbendiger.moviesservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Math.random

@SpringBootTest
class MoviesserviceApplicationTests {

    @Test
    fun contextLoads() {
        val randomNumber = random() * 10
        println("RandomNumber : $randomNumber")
        assert(randomNumber > 5)
    }

}
