package com.example.fox_kt.infra.mail.service

import com.example.fox_kt.infra.mail.domain.MailCode
import com.example.fox_kt.infra.mail.domain.repository.EmailCodeRepository
import com.example.fox_kt.infra.mail.presentation.dto.MailResponse
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class CreateEmailCodeService(
    private val mailSender: JavaMailSender,
    private val emailCodeRepository: EmailCodeRepository
) {

    fun sendVerificationCode(email: String): MailResponse {
        val existingMailCode = emailCodeRepository.findById(email)

        if (existingMailCode.isPresent) {
            return MailResponse(false, "이미 인증 코드가 전송되었습니다.", "")
        }

        val verificationCode = generateVerificationCode()
        val message = SimpleMailMessage()
        message.setTo(email)
        message.setSubject("회원 가입 인증 코드")
        message.setText("인증 코드: $verificationCode")
        mailSender.send(message)

        val newMailCode = MailCode(email, verificationCode)
        emailCodeRepository.save(newMailCode)

        return MailResponse(true, "인증 코드를 이메일로 전송했습니다.", verificationCode)
    }

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
}