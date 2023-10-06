package com.example.fox_kt.domain.interest.presentation

import com.example.fox_kt.domain.interest.presentation.dto.request.InterestRequest
import com.example.fox_kt.domain.interest.service.InterestService
import com.example.fox_kt.domain.interest.service.ModifyInterestService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interest")
class InterestController(
    private val interestService: InterestService,
    private val modifyInterestService : ModifyInterestService
) {
    @PostMapping
    fun selectInterest(interestRequest: InterestRequest) =
        interestService.selectInterest(interestRequest)

    @PatchMapping
    fun modifyInterest(interestRequest: InterestRequest) =
         modifyInterestService.modifyInterest(interestRequest)

}