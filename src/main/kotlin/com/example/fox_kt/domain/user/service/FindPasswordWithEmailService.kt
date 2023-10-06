package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.exception.EmailCodeMissMatchException
import com.example.fox_kt.domain.user.exception.PasswordMissMatchException
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.domain.user.presentation.dto.request.FindPasswordWthEmailRequest
import com.example.fox_kt.infra.mail.domain.MailCode
import com.example.fox_kt.infra.mail.domain.repository.EmailCodeRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FindPasswordWithEmailService(
    private val passwordEncoder: PasswordEncoder,
    private val emailCodeRepository: EmailCodeRepository,
    private val userFacade: UserFacade,
) {
     @Transactional
     fun passwordWithEmail(findPasswordWthEmailRequest: FindPasswordWthEmailRequest) {
         val user : User = userFacade.getUserByEmail(findPasswordWthEmailRequest.email)
         val validEmailCode : Optional<MailCode> = emailCodeRepository.findById(findPasswordWthEmailRequest.email)
         val retrievedCode: MailCode = validEmailCode.get()
         
         if(retrievedCode.emailCode != findPasswordWthEmailRequest.validEmailCode) {
             throw EmailCodeMissMatchException
         }
         if (findPasswordWthEmailRequest.newPassword != findPasswordWthEmailRequest.validPassword) {
             throw PasswordMissMatchException
         }
         user.modifyPassword(passwordEncoder.encode(findPasswordWthEmailRequest.newPassword))
    }
}