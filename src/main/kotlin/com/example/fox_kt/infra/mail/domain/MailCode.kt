package com.example.fox_kt.infra.mail.domain

import lombok.Builder
import lombok.Getter
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class MailCode (
    @Id
    val email : String,

    val emailCode : String
)