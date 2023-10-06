package com.example.fox_kt.global.security.jwt.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class RefreshToken (
    @Id
    val email : String,
    val refreshToken: String,
    val refreshTokenTime : Long
)
