package com.example.fox_kt.domain.school_open_chat.presentation.request

data class SendOpenChatRequest (
    val message: String,
    val openChatRoomId: Long
)