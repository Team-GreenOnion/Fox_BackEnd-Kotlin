package com.example.fox_kt.domain.user.presentation.dto.request

data class FindPasswordWthEmailRequest(
    val email : String,
    val validEmailCode : String,
    val newPassword : String,
    val validPassword : String
)