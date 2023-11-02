package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.domain.repository.UserRepository
import com.example.fox_kt.domain.user.exception.EmailAlreadyExistsException
import com.example.fox_kt.domain.user.exception.EmailCodeMissMatchException
import com.example.fox_kt.domain.user.exception.NameAlreadyExistsException
import com.example.fox_kt.domain.user.exception.PasswordMissMatchException
import com.example.fox_kt.domain.user.presentation.dto.request.UserSignupRequest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignupService (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val stringRedisTemplate: StringRedisTemplate
){
    @Transactional
    fun signup (userSignupRequest: UserSignupRequest) {

        if (userSignupRequest.validEmailCode != stringRedisTemplate.opsForValue().get(userSignupRequest.email)) {
            throw EmailCodeMissMatchException
        }

        if (userRepository.existsByEmail(userSignupRequest.email)) {
            throw EmailAlreadyExistsException
        }
        if (userRepository.existsByName(userSignupRequest.name)) {
            throw NameAlreadyExistsException
        }
        if (userSignupRequest.password != userSignupRequest.validPassword) {
            throw PasswordMissMatchException
        }
        val password = passwordEncoder.encode(userSignupRequest.password)
        val user = User(
            email = userSignupRequest.email,
            password = password,
            name = userSignupRequest.name,
            sex = userSignupRequest.sex,
            id = null,
        )
        userRepository.save(user)
    }
}