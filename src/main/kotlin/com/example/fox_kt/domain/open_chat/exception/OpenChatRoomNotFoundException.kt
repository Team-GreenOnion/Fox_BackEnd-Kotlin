package com.example.fox_kt.domain.open_chat.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode

object OpenChatRoomNotFoundException: BusinessException (
    ErrorCode.OPEN_CHAT_ROOM_NOT_FOUND
)
