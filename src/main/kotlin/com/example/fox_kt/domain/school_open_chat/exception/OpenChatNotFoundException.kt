package com.example.fox_kt.domain.school_open_chat.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode

object OpenChatNotFoundException : BusinessException(
    ErrorCode.OPEN_CHAT_ROOM_NOT_FOUND
)