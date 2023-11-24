package com.example.fox_kt.domain.interest.service

import com.example.fox_kt.domain.interest.domain.Interest
import com.example.fox_kt.domain.interest.domain.repository.InterestRepository
import com.example.fox_kt.domain.interest.presentation.dto.request.InterestRequest
import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyInterestService(
    private val interestRepository: InterestRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun modifyInterest(interestRequest: InterestRequest) {
        val currentUser: User = userFacade.getCurrentUser()
        val interest = Interest(user = currentUser, interest = interestRequest.interest)

        interest.modifyInterest(interest = interestRequest.interest)
    }
}