package com.example.fox_kt.domain.school_open_chat.presentation.request

data class SendMessageRequest (

    val message: String? = null,

    val openChatRoom: Long? = null
)