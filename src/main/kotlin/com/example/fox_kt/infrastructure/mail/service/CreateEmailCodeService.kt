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
    fun generateVerificationCode(): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = SecureRandom()
        val codeBuilder = StringBuilder(6)
        for (i in 0..5) {
            val index = random.nextInt(characters.length)
            codeBuilder.append(characters[index])
        }
        return codeBuilder.toString()
    }

    @Async
    fun sendVerificationCode(email: String): CompletableFuture<String> {
        val verificationCode = generateVerificationCode()
        val message = SimpleMailMessage()
        message.setTo(email)
        message.subject = "회원 가입 인증 코드"
        message.text = "인증 코드: $verificationCode"
        mailSender.send(message)
        redisTemplate.opsForValue()[email] = verificationCode
        redisTemplate.expire(email, 6, TimeUnit.MINUTES)
        return CompletableFuture.completedFuture(verificationCode)
    }
}