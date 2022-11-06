package de.fherfurt.bookingservice.models

import java.time.LocalDate
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "address")
data class Address(
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),

    @Column
    val city: String = "",

    @Column
    val street: String = "",

    @Column
    val zip: String = "",

    @Column(name = "house_number")
    val houseNumber: String = "",
)