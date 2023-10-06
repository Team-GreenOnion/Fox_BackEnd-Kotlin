package com.example.fox_kt.domain.school.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class School(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val atptOfcdcScNm: String,
    val schulNm: String,
    val lctnScNm: String,
    val orgRdnma: String,
    val orgTelno: String,
    val hmpgAdres: String,
    val coeduScNm: String,
    val hsScNm: String
)
