package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.domain.user.presentation.dto.response.QueryMyInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyInfoService(
        private val userFacade: UserFacade
) {
    @Transactional(readOnly = true)
    fun queryMyInfo() : QueryMyInfoResponse {
        val user: User = userFacade.getCurrentUser()

        return QueryMyInfoResponse(
            name = user.name,
            email = user.email,
            sex = user.sex,
            interest = user.interest
        )
    }
}