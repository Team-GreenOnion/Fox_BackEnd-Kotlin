package com.example.fox_kt.domain.open_chat.domain.repository

import com.example.fox_kt.domain.open_chat.domain.OpenChatJoiner
import org.springframework.data.jpa.repository.JpaRepository

interface OpenChatJoinerRepository : JpaRepository<OpenChatJoiner, Long>
