package com.example.fox_kt.domain.user.domain

import com.example.fox_kt.domain.user.enums.Sex
import com.example.fox_kt.domain.user.enums.Type
import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.*

@Entity(name = "tbl_user")
class User(
    id : Long?,

    @Column(name = "email", nullable = false)
    val email:String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "profile_image_url", nullable = true)
    var profileUrl: String? = null,

    @Enumerated(EnumType.STRING)
    val sex: Sex,

    @Enumerated(EnumType.STRING)
    val type: Type,

) : BaseEntity(id) {
    fun modifyPassword(password: String) {
        this.password = password
    }
    fun uploadProfile(profileUrl: String) {
        this.profileUrl = profileUrl
    }
}