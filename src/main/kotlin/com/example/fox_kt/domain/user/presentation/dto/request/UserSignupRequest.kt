package com.example.fox_kt.domain.user.presentation.dto.request

import com.example.fox_kt.domain.user.enums.Sex


class UserSignupRequest(
    val email :String,
    val password : String,
    val validPassword : String,
    val validEmailCode : String,
    val name : String,
    val sex : Sex
)