package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_open_chat_joiner")
class OpenChatJoiner(
    @EmbeddedId
    val id: OpenChatJoinerId,

    @ManyToOne @Id
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne @Id
    @JoinColumn(name = "open_chat_room_id", nullable = false)
    val openChatRoom: OpenChatRoom
) {
    @Embeddable
    data class OpenChatJoinerId(
        val openChatRoom: Long? = null,
        val user: Long? = null
    )
}