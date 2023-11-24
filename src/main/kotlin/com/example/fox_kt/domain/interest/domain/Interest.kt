package com.example.fox_kt.domain.interest.domain

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.*

@Entity
class Interest(
    id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "interest", nullable = false)
    var interest: String
): BaseEntity(id) {
    fun modifyInterest(interest: String) {
        this.interest = interest
    }
}