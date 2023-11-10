package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.exception.EmailCodeMissMatchException
import com.example.fox_kt.domain.user.exception.PasswordMissMatchException
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.domain.user.presentation.dto.request.FindPasswordWthEmailRequest
import com.example.fox_kt.infrastructure.mail.domain.repository.MailRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindPasswordWithEmailService(
    private val passwordEncoder: PasswordEncoder,
    private val userFacade: UserFacade,
    private val mailRepository: MailRepository
) {
     @Transactional
     fun passwordWithEmail(request: FindPasswordWthEmailRequest) {
         val user : User = userFacade.getUserByEmail(request.email)

         val validEmailCode = mailRepository.findByIdOrNull(request.email) ?: throw EmailCodeMissMatchException

         if (request.newPassword != request.validPassword) {
             throw PasswordMissMatchException
         }
         val password = passwordEncoder.encode(request.newPassword)
         user.modifyPassword(password)
    }
}