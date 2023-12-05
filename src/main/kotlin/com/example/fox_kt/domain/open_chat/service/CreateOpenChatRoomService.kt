package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.OpenChatRoom
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateOpenChatRoomService(
    private val userFacade: UserFacade,
    private val openChatRoomRepository: OpenChatRoomRepository
) {
    @Transactional
    fun execute(roomName: String) {
        openChatRoomRepository.save(
            OpenChatRoom(
                id = null,
                roomName = roomName
            )
        )
    }
}