package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@IdClass(OpenChat.IdClass::class)
@Entity(name = "tbl_open_chat")
class OpenChat(

    @ManyToOne @Id
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,


    @ManyToOne @Id
    @JoinColumn(name = "open_chat_room_id", nullable = false)
    val openChatRoom: OpenChatRoom,

    val message: String,

) {
    data class IdClass(
        var openChatRoom: Long? = null,
        var user: Long? = null
    ) : Serializable

    private fun id() = IdClass(
        this.openChatRoom.id,
        this.user.id
    )
}