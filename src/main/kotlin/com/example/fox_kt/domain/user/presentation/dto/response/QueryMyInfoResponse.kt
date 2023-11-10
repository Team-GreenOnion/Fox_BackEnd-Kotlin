package com.example.fox_kt.domain.user.presentation.dto.response

import com.example.fox_kt.domain.interest.domain.Interest
import com.example.fox_kt.domain.user.enums.Sex

data class QueryMyInfoResponse(
        val name: String,
        val email: String,
        val sex: Sex,
        val interest: List<Interest>?
)
