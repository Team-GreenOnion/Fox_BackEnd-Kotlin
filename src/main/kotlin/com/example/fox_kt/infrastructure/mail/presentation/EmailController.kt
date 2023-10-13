package com.example.fox_kt.infrastructure.mail.presentation

import com.example.fox_kt.infrastructure.mail.service.CreateEmailCodeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email/code")
class EmailController(
    private val createEmailCodeService: CreateEmailCodeService,
) {
    @PostMapping
    fun requestEmailCode(@RequestParam email : String) : String? {
        return createEmailCodeService.sendVerificationCode(email)
    }
}