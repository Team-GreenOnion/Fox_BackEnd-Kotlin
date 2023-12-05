package com.example.fox_kt.domain.user.presentation.dto.response

import com.example.fox_kt.domain.user.enums.Sex

data class QueryMyInfoResponse(
        val name: String,
        val email: String,
        val profileUrl: String?,
        val sex: Sex,
)
