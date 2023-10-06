package com.example.fox_kt.domain.interest.domain.repository

import com.example.fox_kt.domain.interest.domain.Interest
import org.springframework.data.jpa.repository.JpaRepository

interface InterestRepository : JpaRepository<Interest, Long>{
}