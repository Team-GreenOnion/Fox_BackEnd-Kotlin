package com.example.fox_kt.infra.mail.service

import com.example.fox_kt.infra.mail.domain.repository.EmailCodeRepository
import com.example.fox_kt.infra.mail.presentation.dto.MailResponse
import org.springframework.stereotype.Service

@Service
class ResendCodeRequestService(
    private val createEmailCodeService: CreateEmailCodeService,
    private val emailCodeRepository: EmailCodeRepository
) {
    fun ResendCode(email : String) : MailResponse {
        emailCodeRepository.deleteById(email)
        return createEmailCodeService.sendVerificationCode(email)
    }
}