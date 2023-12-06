package com.example.fox_kt.domain.open_chat.domain.repository

import com.example.fox_kt.domain.open_chat.domain.OpenChat
import com.example.fox_kt.domain.open_chat.domain.OpenChatJoiner
import com.example.fox_kt.domain.open_chat.domain.OpenChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface OpenChatJoinerRepository : JpaRepository<OpenChatJoiner, OpenChatJoiner.IdClass>{
    fun findAllByOpenChatRoom(chatRoom: OpenChatRoom): List<OpenChatJoiner>
}
