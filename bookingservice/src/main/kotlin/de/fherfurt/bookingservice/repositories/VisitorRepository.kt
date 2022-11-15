package de.fherfurt.bookingservice.repositories

import de.fherfurt.bookingservice.models.Visitor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VisitorRepository : CrudRepository<Visitor, Long> {
    fun findVisitorById(userId: Long): Visitor
}