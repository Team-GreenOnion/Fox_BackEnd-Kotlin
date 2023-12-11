package com.example.fox_kt.infrastructure.mail.presentation.dto

data class MailResponse (
    val success : Boolean,
    val message : String,
    val emailCode : String
)