package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.OpenChatJoiner
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatJoinerRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.open_chat.exception.OpenChatRoomNotFoundException
import com.example.fox_kt.domain.user.exception.UserNotFoundException
import com.example.fox_kt.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JoinOpenChatService(
    private val userFacade: UserFacade,
    private val openChatRoomRepository: OpenChatRoomRepository,
    private val openChatJoinerRepository: OpenChatJoinerRepository
) {

    @Transactional
    fun execute(roomId: Long) {
        val user = userFacade.getCurrentUser()
        val room = openChatRoomRepository.findByIdOrNull(roomId)?: throw  OpenChatRoomNotFoundException
        if (openChatJoinerRepository.existsByUser(user)) throw UserNotFoundException
        openChatJoinerRepository.save(
            OpenChatJoiner(
                user = user,
                openChatRoom = room
            )
        )
    }
}