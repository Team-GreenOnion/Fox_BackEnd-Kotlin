package com.example.fox_kt.domain.school_open_chat.presentation

import com.example.fox_kt.domain.school_open_chat.presentation.request.CreateOpenChatRequest
import com.example.fox_kt.domain.school_open_chat.service.CreateOpenChatService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class OpenChatController(
    private val createOpenChatService: CreateOpenChatService
) {
    @PostMapping
    fun createOpenChat(@RequestBody createOpenChatRequest: CreateOpenChatRequest) =
        createOpenChatService.createOpenChat(createOpenChatRequest)
}