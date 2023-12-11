package com.example.fox_kt.domain.open_chat.presentation.dto.response

import com.example.fox_kt.domain.open_chat.domain.OpenChat
import java.time.LocalDateTime

data class ReceiveChatResponse (
    val message: String,
    val sender: String,
    val roomName: String,
    val sendTime: LocalDateTime
) {
    companion object {
        fun of(openChat: OpenChat): ReceiveChatResponse =
            ReceiveChatResponse(
                message = openChat.message,
                sender = openChat.user.name,
                roomName = openChat.openChatRoom.roomName,
                sendTime = openChat.createAt
            )
    }
}
