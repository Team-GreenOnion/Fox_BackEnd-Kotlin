package com.example.fox_kt.domain.open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@IdClass(OpenChatJoiner.IdClass::class)
@Entity(name = "tbl_joiner")
class OpenChatJoiner(

    @Id @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    var openChatRoom: OpenChatRoom,

    @Id @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
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
