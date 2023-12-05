package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.OpenChat
import com.example.fox_kt.domain.open_chat.domain.OpenChatRoom
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatJoinerRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
import com.example.fox_kt.domain.open_chat.exception.OpenChatRoomNotFoundException
import com.example.fox_kt.domain.open_chat.presentation.dto.request.SendChatDto
import com.example.fox_kt.domain.open_chat.presentation.dto.response.ReceiveChatResponse
import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.exception.UserNotFoundException
import com.example.fox_kt.domain.user.facade.UserFacade
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import javax.websocket.OnClose
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(value = "/chat")
class ChatSocketService(
    private val openChatRoomRepository: OpenChatRoomRepository,
    private val openChatJoinerRepository: OpenChatJoinerRepository,
    private val openChatRepository: OpenChatRepository,
    private val userFacade: UserFacade,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        private val clients: ArrayList<Session> = ArrayList()
    }
    @OnOpen
    fun socketOpen(session: Session) {
        objectMapper.registerModule(JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        clients.add(session)
    }

    @OnClose
    fun socketClose(session: Session) = clients.remove(session)

    @OnMessage
    fun socketSend(session: Session, message: String) {
        val request = objectMapper.readValue(message, SendChatDto::class.java)
        val sender = userFacade.getUserByEmail(session.userPrincipal.name)?: throw UserNotFoundException
        val sendOpenChat = openChatRoomRepository.findByRoomName(request.chatRoomName)?: throw OpenChatRoomNotFoundException

        val chatRoomParticipants = orderChatRoomParticipants(sendOpenChat, sender)
        val chat = openChatRepository.save(OpenChat(user = sender, openChatRoom = sendOpenChat, message = request.message))

        sendOpenChat(chat, chatRoomParticipants)
    }

    private fun orderChatRoomParticipants(sendTargetChatRoom: OpenChatRoom, sender: User) =
        openChatRepository.findAllByOpenChatRoom(sendTargetChatRoom)
            .map { joiner -> clients.first { it.userPrincipal.name == joiner.user.name } }
            .filter { it.userPrincipal.name != sender.name }

    private fun sendOpenChat(openChat: OpenChat, chatRoomParticipants: List<Session>) {
        val openChatResponse = objectMapper.writeValueAsString(ReceiveChatResponse.of(openChat))
        chatRoomParticipants.map { it.basicRemote.sendText(openChatResponse) }
    }
}
