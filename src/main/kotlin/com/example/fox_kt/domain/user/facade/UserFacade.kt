package com.example.fox_kt.domain.user.facade

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.domain.repository.UserRepository
import com.example.fox_kt.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name
        return getUserByEmail(email)
    }

    fun getUserByEmail(email: String): User =
        userRepository.findByEmail(email) ?: throw UserNotFoundException
}