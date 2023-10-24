package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "open_chat_joiner")
class OpenChatJoiner(
    @EmbeddedId
    var id: OpenChatJoinerId,

    @ManyToOne @Id
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne @Id
    @JoinColumn(name = "open_chat_room_id", nullable = false)
    var openChatRoom: OpenChatRoom
) {
    @Embeddable
    data class OpenChatJoinerId(
        var openChatRoom: Long? = null,
        var user: Long? = null
    )
}