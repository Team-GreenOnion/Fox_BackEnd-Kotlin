package com.example.fox_kt.infrastructure.mail.presentation

import com.example.fox_kt.infrastructure.mail.service.CreateEmailCodeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@Tag(name = "email", description = "이메일 인증코드 API")
@RestController
@RequestMapping("/email/code")
class EmailController(
    private val createEmailCodeService: CreateEmailCodeService,
) {

    @Operation(summary = "이메일 인증번호 생성")
    @PostMapping
    fun requestEmailCode(@RequestParam email: String): CompletableFuture<String> =
     createEmailCodeService.sendVerificationCode(email)

}