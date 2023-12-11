package com.example.fox_kt.domain.open_chat.domain.repository

import com.example.fox_kt.domain.open_chat.domain.OpenChat
import com.example.fox_kt.domain.open_chat.domain.OpenChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface OpenChatRepository: JpaRepository<OpenChat, OpenChat.IdClass>
