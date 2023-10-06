package com.example.fox_kt.global.security.jwt.repository

import com.example.fox_kt.global.security.jwt.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {
    fun existsByEmail(email : String) : Boolean
    fun findByEmail(email : String) : Optional<RefreshToken>
}