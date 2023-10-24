package com.example.fox_kt.domain.school_open_chat.service

import com.example.fox_kt.domain.school_open_chat.domain.OpenChatRoom
import com.example.fox_kt.domain.school_open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.school_open_chat.presentation.request.CreateOpenChatRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateOpenChatService (
    private val openChatRoomRepository: OpenChatRoomRepository
){

    @Transactional
    fun createOpenChat(request: CreateOpenChatRequest) {
        val openChatRoom = OpenChatRoom(
            roomName = request.chatRoomName,
            id = null
        )
        openChatRoomRepository.save(openChatRoom)
    }
}