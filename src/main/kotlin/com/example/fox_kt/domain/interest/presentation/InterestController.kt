package com.example.fox_kt.domain.interest.presentation

import com.example.fox_kt.domain.interest.presentation.dto.request.InterestRequest
import com.example.fox_kt.domain.interest.service.InterestService
import com.example.fox_kt.domain.interest.service.ModifyInterestService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "interest", description = "관심분야 선택")
@RestController
@RequestMapping("/interest")
class InterestController(
    private val interestService: InterestService,
    private val modifyInterestService : ModifyInterestService
) {

    @Operation(summary = "관심분야 선택")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun selectInterest(interestRequest: InterestRequest) =
        interestService.selectInterest(interestRequest)

    @Operation(summary = "관심분야 수정")
    @PatchMapping
    fun modifyInterest(interestRequest: InterestRequest) =
         modifyInterestService.modifyInterest(interestRequest)

}