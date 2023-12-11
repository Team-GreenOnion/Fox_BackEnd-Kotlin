package com.example.fox_kt.domain.user.presentation.dto.request

data class ModifyPasswordRequest (
    val password : String,
    val newPassword : String,
    val validPassword : String,
)