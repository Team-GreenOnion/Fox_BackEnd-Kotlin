package com.example.fox_kt.domain.open_chat.domain

import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity(name = "tbl_open_chat_room")
class OpenChatRoom(
    id: Long?,
    @Column(name = "room_name",nullable = false, unique = true)
    val roomName: String,

    @OneToMany(mappedBy = "openChatRoom")
    val joiner: List<OpenChatJoiner>? = null
): BaseEntity(id)
