package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import org.joda.time.DateTime
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_open_chat")
class OpenChat(

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "open_chat_room_id", nullable = false)
    val openChatRoom: OpenChatRoom,

    val message: String,

) {
    @Embeddable
    data class OpenChatId(
        val user: Long? = null,
        val openChatRoom: Long? = null
    )
}