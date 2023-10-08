package com.example.fox_kt.infra.mail.service

import com.example.fox_kt.infra.mail.presentation.dto.MailResponse
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class ResendCodeRequestService(
    private val createEmailCodeService: CreateEmailCodeService,
    private val stringRedisTemplate : StringRedisTemplate
) {
    fun ResendCode(email : String) : String? {
        stringRedisTemplate.delete(email);
        return createEmailCodeService.sendVerificationCode(email);
    }
}