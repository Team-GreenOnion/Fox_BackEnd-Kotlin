package com.example.fox_kt.domain.user.presentation.dto.request

class FindPasswordWthEmailRequest(
    val email : String,
    val validEmailCode : String,
    val newPassword : String,
    val validPassword : String
)