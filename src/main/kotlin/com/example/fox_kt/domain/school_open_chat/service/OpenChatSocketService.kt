package com.example.fox_kt.domain.school_open_chat.service

import com.example.fox_kt.domain.school_open_chat.domain.OpenChat
import com.example.fox_kt.domain.school_open_chat.domain.OpenChatRoom
import com.example.fox_kt.domain.school_open_chat.domain.repository.OpenChatJoinerRepository
import com.example.fox_kt.domain.school_open_chat.domain.repository.OpenChatRepository
import com.example.fox_kt.domain.school_open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.school_open_chat.exception.OpenChatNotFoundException
import com.example.fox_kt.domain.school_open_chat.presentation.request.SendOpenChatRequest
import com.example.fox_kt.domain.school_open_chat.presentation.response.ReceiveOpenChatResponse
import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.domain.repository.UserRepository
import com.example.fox_kt.domain.user.exception.UserNotFoundException
import com.example.fox_kt.global.config.socket.ServerEndpointConfigurator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.websocket.OnClose
import javax.websocket.OnOpen
import javax.websocket.OnMessage
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(value = "/chat", configurator = ServerEndpointConfigurator::class)
@Service
class ChatSocketService(
    private val openChatRepository: OpenChatRepository,
    private val openChatRoomRepository: OpenChatRoomRepository,
    private val openChatJoinerRepository: OpenChatJoinerRepository,
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper
) {
    companion object {
        private val clients = ArrayList<Session>()
    }

    @OnOpen
    fun socketOpen(session: Session) {
        objectMapper.registerModule(JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        clients.add(session)
    }

    @OnClose
    fun socketClose(session: Session) {
        clients.remove(session)
    }

    @OnMessage
    fun socketSend(session: Session, message: String) {

        val dto = objectMapper.readValue(message, SendOpenChatRequest::class.java)

        val sender = userRepository.findByName(session.userPrincipal.name) ?: throw UserNotFoundException
        val sendTargetChatRoom = openChatRoomRepository.findByIdOrNull(dto.openChatRoomId) ?: throw OpenChatNotFoundException

        val chatRoomParticipants = orderChatRoomParticipants(sendTargetChatRoom, sender)
        val chat = openChatRepository.save(OpenChat(user = sender, openChatRoom = sendTargetChatRoom, message = dto.message))

        sendChat(chat, chatRoomParticipants)
    }

    private fun orderChatRoomParticipants(sendTargetChatRoom: OpenChatRoom, sender: User) =
        openChatJoinerRepository.findAllByChatRoom(sendTargetChatRoom)
            .map { joiner -> clients.first { it.userPrincipal.name == joiner.user.name } }
            .filter { it.userPrincipal.name != sender.name }

    private fun sendChat(openChat: OpenChat, chatRoomParticipants: List<Session>) {
        val chatDto = objectMapper.writeValueAsString(ReceiveOpenChatResponse.of(openChat))
        chatRoomParticipants.map { it.basicRemote.sendText(chatDto) }
    }
}
