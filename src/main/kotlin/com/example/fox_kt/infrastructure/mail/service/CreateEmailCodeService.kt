package com.example.fox_kt.infrastructure.mail.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


@Service
class CreateEmailCodeService (
    private val mailSender: JavaMailSender,
    private val redisTemplate: StringRedisTemplate
){
    @Async
    fun sendVerificationCode(email: String): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = SecureRandom()
        val codeBuilder = StringBuilder(6)

        for (i in 0..5) {
            val index = random.nextInt(characters.length)
            codeBuilder.append(characters[index])
        }

        val verificationCode = codeBuilder.toString()

        val message = SimpleMailMessage().apply {
            setTo(email)
            subject = "회원 가입 인증 코드"
            text = "인증 코드: $verificationCode"
        }

        mailSender.send(message)

        redisTemplate.opsForValue().set(email, verificationCode)
        redisTemplate.expire(email, 6, TimeUnit.MINUTES)

        return verificationCode
    }

}