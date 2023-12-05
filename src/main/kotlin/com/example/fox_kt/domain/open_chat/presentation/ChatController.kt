package com.example.fox_kt.domain.open_chat.presentation

import com.example.fox_kt.domain.open_chat.presentation.dto.request.CreateOpenChatRequest
import com.example.fox_kt.domain.open_chat.service.CreateOpenChatRoomService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController(
    private val createOpenChatRoomService: CreateOpenChatRoomService
) {
    @PostMapping
    fun createChatRoom(@RequestBody request: CreateOpenChatRequest) = createOpenChatRoomService.execute(request)
}
