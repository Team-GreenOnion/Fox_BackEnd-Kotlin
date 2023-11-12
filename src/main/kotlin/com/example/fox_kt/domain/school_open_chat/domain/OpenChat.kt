package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import org.joda.time.DateTime
import java.io.Serializable
import javax.persistence.*

@IdClass(OpenChat.IdClass::class)
@Entity(name = "tbl_open_chat")
class OpenChat(

    @ManyToOne @Id
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,


    @ManyToOne @Id
    @JoinColumn(name = "open_chat_room_id", nullable = false)
    val openChatRoom: OpenChatRoom,

    @Column(name = "message", nullable = false)
    val message: String,
    
    val createAt: DateTime

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