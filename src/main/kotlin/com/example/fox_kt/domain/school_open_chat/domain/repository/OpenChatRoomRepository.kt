package com.example.fox_kt.domain.school_open_chat.domain.repository

import com.example.fox_kt.domain.school_open_chat.domain.OpenChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OpenChatRoomRepository: JpaRepository<OpenChatRoom, Long> {
}