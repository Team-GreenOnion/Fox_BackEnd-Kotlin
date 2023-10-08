package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.exception.EmailCodeMissMatchException
import com.example.fox_kt.domain.user.exception.PasswordMissMatchException
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.domain.user.presentation.dto.request.FindPasswordWthEmailRequest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindPasswordWithEmailService(
    private val passwordEncoder: PasswordEncoder,
    private val userFacade: UserFacade,
    private val stringRedisTemplate: StringRedisTemplate
) {
     @Transactional
     fun passwordWithEmail(findPasswordWthEmailRequest: FindPasswordWthEmailRequest) {
         val user : User = userFacade.getUserByEmail(findPasswordWthEmailRequest.email)


         if (findPasswordWthEmailRequest.email!=(stringRedisTemplate.opsForValue().get(findPasswordWthEmailRequest.email))) {
             throw EmailCodeMissMatchException
         }


         if (findPasswordWthEmailRequest.newPassword != findPasswordWthEmailRequest.validPassword) {
             throw PasswordMissMatchException
         }
         user.modifyPassword(passwordEncoder.encode(findPasswordWthEmailRequest.newPassword))
    }
}