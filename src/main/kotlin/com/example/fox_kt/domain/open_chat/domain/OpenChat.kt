package com.example.fox_kt.domain.open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@IdClass(OpenChat.IdClass::class)
@Entity(name = "tbl_chat")
class OpenChat(

    @Id @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Id @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    val openChatRoom: OpenChatRoom,

    @Column(name = "message", nullable = false)
    val message: String,

    val createAt: LocalDateTime = LocalDateTime.now()
) {

    data class IdClass(
        var chatRoom: Long? = null,
        var user: Long? = null
    ) : Serializable

    private fun id() = IdClass(
        this.openChatRoom.id,
        this.user.id
    )
}
