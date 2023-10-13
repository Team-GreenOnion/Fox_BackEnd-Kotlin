package com.example.fox_kt.domain.school.presentation

import com.example.fox_kt.infrastructure.feign.SchoolFeignService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SchoolController(
    private val schoolFeignService: SchoolFeignService
) {
    @GetMapping("schools")
    fun feign() {
        schoolFeignService.fetchAndSaveSchoolInfoToDB()
    }
}