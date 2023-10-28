package com.example.fox_kt.infrastructure.mail.presentation

import com.example.fox_kt.infrastructure.mail.service.CreateEmailCodeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email/code")
class EmailController(
    private val createEmailCodeService: CreateEmailCodeService,
) {

    @Operation(summary = "이메일 인증번호 생성")
    @PostMapping
    fun requestEmailCode(@RequestParam email : String) : String? =
         createEmailCodeService.sendVerificationCode(email)
}