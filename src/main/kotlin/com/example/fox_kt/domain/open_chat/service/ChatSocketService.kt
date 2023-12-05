package com.example.fox_kt.domain.open_chat.service

import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatJoinerRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRepository
import com.example.fox_kt.domain.open_chat.domain.repository.OpenChatRoomRepository
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
    private val objectMapper: ObjectMapper
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

    }

}