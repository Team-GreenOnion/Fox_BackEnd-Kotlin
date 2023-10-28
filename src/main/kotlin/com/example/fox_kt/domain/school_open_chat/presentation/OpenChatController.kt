package com.example.fox_kt.domain.school_open_chat.presentation

import com.example.fox_kt.domain.school_open_chat.presentation.request.CreateOpenChatRequest
import com.example.fox_kt.domain.school_open_chat.service.CreateOpenChatService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "open_chat", description = "오픈챗 API")
@RestController
@RequestMapping("/chat")
class OpenChatController(
    private val createOpenChatService: CreateOpenChatService
) {

    @Operation(summary = "오픈채팅방 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createOpenChat(@RequestBody createOpenChatRequest: CreateOpenChatRequest) =
        createOpenChatService.createOpenChat(createOpenChatRequest)
}