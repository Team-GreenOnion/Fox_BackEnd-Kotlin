package com.example.fox_kt.domain.school_open_chat.presentation.response

import com.example.fox_kt.domain.school_open_chat.domain.OpenChat
import org.joda.time.DateTime

data class ReceiveOpenChatResponse(
    val message: String,

    val sender: String,

    val chatRoomName: String,

    val chatRoomId: Long,

) {
    companion object {
        fun of(openChat: OpenChat): ReceiveOpenChatResponse =
            ReceiveOpenChatResponse(
                message = openChat.message,
                sender = openChat.user.name,
                chatRoomName = openChat.openChatRoom.roomName,
                chatRoomId = openChat.openChatRoom.id!!
            )
    }
}
