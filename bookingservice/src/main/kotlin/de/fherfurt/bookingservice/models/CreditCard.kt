package de.fherfurt.bookingservice.models

import java.time.LocalDate
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "credit_card")
data class CreditCard(
    @Id
    @Column(name = "credit_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),

    @Column
    val number: String = "",

    @Column
    val cvc: Int,

    @Column
    val expMonth: Int,

    @Column
    val expYear: Int,
)