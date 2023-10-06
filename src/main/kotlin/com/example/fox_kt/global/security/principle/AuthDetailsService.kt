package com.example.fox_kt.global.security.principle

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(email : String?) : UserDetails {
        val user: User? = email?.let { userFacade.getUserByEmail(it) }
        return AuthDetails(user!!.email)
    }
}