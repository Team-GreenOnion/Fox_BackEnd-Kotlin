package com.example.fox_kt.infrastructure.mail.domain

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash
class Mail (
    @Id
    val email : String,

    val emailCode : String
)