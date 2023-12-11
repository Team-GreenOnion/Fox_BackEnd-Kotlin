package com.example.fox_kt.infrastructure.mail.service

import com.example.fox_kt.infrastructure.mail.domain.Mail
import com.example.fox_kt.infrastructure.mail.domain.repository.MailRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.concurrent.TimeUnit


@Service
class CreateEmailCodeService(
    private val mailSender: JavaMailSender,
    private val mailRepository: MailRepository
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

    fun sendVerificationCode(email: String): String {
        mailRepository.deleteAll()
        val verificationCode = generateVerificationCode()
        val message = SimpleMailMessage()
        message.setTo(email)
        val mail = Mail(
                email = email,
                emailCode = verificationCode
        )
        message.subject = "회원 가입 인증 코드"
        message.text = "인증 코드: $verificationCode"
        mailSender.send(message)

        mailRepository.save(mail)
        return verificationCode
    }
}