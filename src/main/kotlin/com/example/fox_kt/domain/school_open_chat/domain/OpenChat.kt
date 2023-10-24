package com.example.fox_kt.domain.school_open_chat.domain

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class SchoolOpenChat(
    id : Long?,

    @ManyToOne
    val  user: User,

) : BaseEntity(id)