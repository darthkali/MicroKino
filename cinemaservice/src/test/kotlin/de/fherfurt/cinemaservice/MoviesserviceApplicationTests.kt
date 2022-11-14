package de.fherfurt.cinemaservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Math.random

@SpringBootTest
class CinemasserviceApplicationTests {

    @Test
    fun contextLoads() {
        val randomNumber = random() * 10
        println("RandomNumber : $randomNumber")
        //assert(randomNumber > 5)
    }

}
