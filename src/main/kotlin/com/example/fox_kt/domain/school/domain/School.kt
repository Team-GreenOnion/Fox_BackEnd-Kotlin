package com.example.fox_kt.domain.school.domain

import com.example.fox_kt.global.entity.BaseEntity
import javax.persistence.Entity

@Entity(name = "tbl_school")
class School (
    id:Long?,
):BaseEntity(id)