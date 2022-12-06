package de.fherfurt.movieservice.models

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "show")
data class Show(
    @Id
    @Column(name = "show_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "create_dt")
    val createDt: LocalDate? = LocalDate.now(),

    @Column
    val movieId: Long,
)