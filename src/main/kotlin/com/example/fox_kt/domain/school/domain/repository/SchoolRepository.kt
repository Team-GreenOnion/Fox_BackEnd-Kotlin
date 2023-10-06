package com.example.fox_kt.domain.school.domain.repository

import com.example.fox_kt.domain.school.domain.School
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, Long>{
}