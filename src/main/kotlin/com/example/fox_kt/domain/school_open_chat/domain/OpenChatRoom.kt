package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity(name = "tbl_open_chat_room")
class OpenChatRoom(
    id: Long?,

    @Column(name = "open_chat_name", nullable = false)
    val roomName: String,

    @OneToMany(mappedBy = "openChatRoom")
    var chatJoiner: MutableList<OpenChatJoiner> = mutableListOf()

): BaseEntity(id)