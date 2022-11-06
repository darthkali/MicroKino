package de.fherfurt.bookingservice.repositories

import de.fherfurt.bookingservice.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserById(userId: Long): User
}