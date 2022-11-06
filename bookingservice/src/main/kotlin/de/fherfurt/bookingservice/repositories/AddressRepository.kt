package de.fherfurt.bookingservice.repositories

import de.fherfurt.bookingservice.models.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : CrudRepository<Address, Long> {
    fun findAddressById(bookingId: Long): Address
}