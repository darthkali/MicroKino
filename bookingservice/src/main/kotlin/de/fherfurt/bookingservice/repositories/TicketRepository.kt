package de.fherfurt.bookingservice.repositories

import de.fherfurt.bookingservice.models.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, Long> {
    fun findTicketById(ticketId: Long): Ticket
}