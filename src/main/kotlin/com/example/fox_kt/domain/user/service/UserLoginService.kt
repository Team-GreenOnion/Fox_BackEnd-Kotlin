package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.exception.PasswordMissMatchException
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.domain.user.presentation.dto.request.UserLoginRequest
import com.example.fox_kt.global.security.jwt.JwtProvider
import com.example.fox_kt.global.security.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val passwordEncoder: PasswordEncoder,
    private val userFacade: UserFacade,
    private val jwtProvider: JwtProvider
) {
    fun login(userLoginRequest: UserLoginRequest) : TokenResponse {
        val user : User = userFacade.getUserByEmail(userLoginRequest.email)
        if (!passwordEncoder.matches(userLoginRequest.password, user.password)) {
            throw PasswordMissMatchException
        }
        return jwtProvider.getToken(user)
    }
}