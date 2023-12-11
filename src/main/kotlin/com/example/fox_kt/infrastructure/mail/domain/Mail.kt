package com.example.fox_kt.infrastructure.mail.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class Mail (
    @Id
    val email : String,

    val emailCode : String
)