package com.example.fox_kt.domain.user.service

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.domain.user.facade.UserFacade
import com.example.fox_kt.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UploadUserProfileService (
    private val userFacade: UserFacade,
    private val s3Service: S3Service
){
    @Transactional
    fun uploadProfile(profile: MultipartFile) {
        val user : User = userFacade.getCurrentUser()
        val profileUrl : String = s3Service.uploadImage(profile)

        user.uploadProfile(profileUrl)
    }
}