package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.OpenChatJoiner
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatJoinerRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JoinRoomService(
    private val userFacade: UserFacade,
    private val joinerRepository: OpenChatJoinerRepository,
    private val openChatRoomRepository: OpenChatRoomRepository
) {
    @Transactional
    fun execute(roomName: String){
        val user = userFacade.getCurrentUser()
        val room = openChatRoomRepository.findByRoomName(roomName)
        joinerRepository.save(
            OpenChatJoiner(
                openChatRoom = room,
                user = user
            )
        )
    }
}