package com.example.fox_kt.domain.school_open_chat.service

import com.example.fox_kt.domain.school_open_chat.domain.*
import com.example.fox_kt.domain.school_open_chat.domain.repository.*
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
import org.joda.time.DateTime
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

    init {
        objectMapper.registerModule(JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    @OnOpen
    fun socketOpen(session: Session) {
        clients.add(session)
    }

    @OnClose
    fun socketClose(session: Session) {
        clients.remove(session)
    }

    @OnMessage
    fun socketSend(session: Session, message: String) {
            val openChatRequest = objectMapper.readValue(message, SendOpenChatRequest::class.java)
            val sender = findUserBySession(session) ?: throw UserNotFoundException
            val sendTargetChatRoom = openChatRoomRepository.findByIdOrNull(openChatRequest.openChatRoomId) ?: throw OpenChatNotFoundException
            val sendAt = DateTime.now()
            val chatRoomParticipants = openChatRoomParticipants(sendTargetChatRoom, sender)
            val chat = createAndSaveChat(sender, sendTargetChatRoom, openChatRequest.message, sendAt)

            sendChat(chat, chatRoomParticipants)
    }

    private fun findUserBySession(session: Session): User? {
        return userRepository.findByName(session.userPrincipal.name)
    }

    private fun openChatRoomParticipants(sendTargetChatRoom: OpenChatRoom, sender: User): List<Session> {
        return openChatJoinerRepository.findAllByOpenChatRoom(sendTargetChatRoom)
            .mapNotNull { joiner -> clients.firstOrNull { it.userPrincipal.name == joiner.user.name } }
            .filter { it.userPrincipal.name != sender.name }
    }

    private fun createAndSaveChat(sender: User, sendTargetChatRoom: OpenChatRoom, message: String, createAt: DateTime): OpenChat {
        return openChatRepository.save(OpenChat(user = sender, openChatRoom = sendTargetChatRoom, message = message, createAt = createAt))
    }

    private fun sendChat(openChat: OpenChat, chatRoomParticipants: List<Session>) {
        val receiveOpenChatResponse = objectMapper.writeValueAsString(ReceiveOpenChatResponse.of(openChat))
        chatRoomParticipants.forEach { it.basicRemote.sendText(receiveOpenChatResponse) }
    }
}
