package com.example.fox_kt.global.security.jwt.dto

class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: Long
)