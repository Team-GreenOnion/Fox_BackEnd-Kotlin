package com.example.fox_kt.domain.user.presentation.dto.request

import com.example.fox_kt.domain.user.enums.Sex
import com.example.fox_kt.domain.user.enums.Type
import org.jetbrains.annotations.NotNull


data class UserSignupRequest(
    @NotNull
    val email :String,
    @NotNull
    val password : String,
    @NotNull
    val validPassword : String,
    @NotNull
    val validEmailCode : String,
    @NotNull
    val name : String,
    @NotNull
    val interest: String,
    @NotNull
    val sex : Sex,
    @NotNull
    val type: Type
)
