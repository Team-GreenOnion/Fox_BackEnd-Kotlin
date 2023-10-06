package com.example.fox_kt.domain.user.domain

import com.example.fox_kt.domain.interest.domain.Interest
import com.example.fox_kt.domain.user.enums.Sex
import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany

@Entity
class User(
    id : Long?,
    val email:String,
    var password: String,
    val name: String,
    var profileUrl: String? = null,
    @Enumerated(EnumType.STRING)
    val sex: Sex,
    @OneToMany
    val interest: List<Interest>? = null
) : BaseEntity(id) {
    fun modifyPassword(password: String) {
        this.password = password
    }
    fun uploadProfile(profileUrl: String) {
        this.profileUrl = profileUrl
    }
}