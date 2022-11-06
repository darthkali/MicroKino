package de.fherfurt.bookingservice.models

import java.time.LocalDate
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "create_dt")
    private val createDt: LocalDate? = LocalDate.now(),

    @Column
    val name: String = "",

    @Column
    val lastName: String,

    @Column
    val email: String,

    @Column
    val phoneNumber: String,

    @Column
    val creditCardId: Int,

    @Column
    val membership: Boolean,

    @ManyToOne
    val address: Address,

    )