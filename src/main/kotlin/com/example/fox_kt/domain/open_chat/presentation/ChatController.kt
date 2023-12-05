package com.example.fox_kt.domain.open_chat.presentation

import com.example.fox_kt.domain.open_chat.service.CreateOpenChatRoomService
import com.example.fox_kt.domain.open_chat.service.JoinRoomService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("'/chat")
class ChatController(
    private val createOpenChatRoomService: CreateOpenChatRoomService,
    private val joinRoomService: JoinRoomService
) {
    @PostMapping
    fun createChatRoom(@RequestBody roomName: String) = createOpenChatRoomService.execute(roomName)

    @PostMapping
    fun joinRoom(@RequestParam roomName: String) = joinRoomService.execute(roomName)
}
