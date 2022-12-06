package de.fherfurt.bookingservice

import de.fherfurt.bookingservice.models.Address
import de.fherfurt.bookingservice.models.CreditCard
import de.fherfurt.bookingservice.models.Ticket
import de.fherfurt.bookingservice.models.Visitor
import de.fherfurt.bookingservice.repositories.AddressRepository
import de.fherfurt.bookingservice.repositories.CreditCardRepository
import de.fherfurt.bookingservice.repositories.TicketRepository
import de.fherfurt.bookingservice.repositories.VisitorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class BookingServiceController {
    @Autowired
    val creditCardRepository: CreditCardRepository? = null

    @Autowired
    val ticketRepository: TicketRepository? = null

    @Autowired
    val addressRepository: AddressRepository? = null

    @Autowired
    val visitorRepository: VisitorRepository? = null


    @GetMapping("/creditCards")
    fun getAllBookings(): List<CreditCard>? {
        return creditCardRepository?.findAll()?.toList()
    }

    @GetMapping("/creditCard/{creditCardId}")
    fun getCreditCardById(@PathVariable(value = "creditCardId") creditCardId: Long): CreditCard? {
        return creditCardRepository?.findCreditCardById(creditCardId)
    }

    @GetMapping("/tickets")
    fun getAllTickets(): List<Ticket>? {
        return ticketRepository?.findAll()?.toList()
    }

    @GetMapping("/ticket/{ticketId}")
    fun getTicketById(@PathVariable(value = "ticketId") ticketId: Long): Ticket? {
        return ticketRepository?.findTicketById(ticketId)
    }

    @GetMapping("/address/{addressId}")
    fun getAddressById(@PathVariable(value = "addressId") addressId: Long): Address? {
        return addressRepository?.findAddressById(addressId)
    }

    @GetMapping("/users")
    fun getAllBookingsHalls(): List<Visitor>? {
        return visitorRepository?.findAll()?.toList()
    }

    @GetMapping("/user/{userId}")
    fun getVisitorById(@PathVariable(value = "userId") userId: Long): Visitor? {
        return visitorRepository?.findVisitorById(userId)
    }
}