package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.OpenChatRoom
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.open_chat.presentation.dto.request.CreateOpenChatRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateOpenChatRoomService(
    private val openChatRoomRepository: OpenChatRoomRepository
) {
    @Transactional
    fun execute(request: CreateOpenChatRequest) {
        openChatRoomRepository.save(
            OpenChatRoom(
                id = null,
                roomName = request.roomName
            )
        )
    }
}
