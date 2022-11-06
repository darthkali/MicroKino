package de.fherfurt.bookingservice.models

import java.time.LocalDate
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "ticket")
data class Ticket(
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),

    @Column
    val cinema: String = "",

    @Column
    val schedule: String,

    @Column
    val movie: String,

    @Column
    val seat: Int,

    @Column
    val cinemaHall: Int,

    @Column
    val orderId: String,

    @Column
    val userId: Long,

    )