package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity(name = "open_chat_room")
class OpenChatRoom (
    id : Long?,

    val roomName: String,

    @OneToMany(mappedBy = "openChatRoom")
    var chatJoiner: MutableList<OpenChatJoiner> = mutableListOf()

): BaseEntity(id)