package com.example.fox_kt.domain.user.domain.repository

import com.example.fox_kt.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email : String) : Optional<User>
    fun existsByEmail(email: String) : Boolean
    fun existsByName(name: String) : Boolean

    fun findByName(name : String) : User?
}