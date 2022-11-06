package de.fherfurt.bookingservice.repositories

import de.fherfurt.bookingservice.models.CreditCard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditCardRepository : CrudRepository<CreditCard, Long> {
    fun findCreditCardById(creditCardId: Long): CreditCard
}