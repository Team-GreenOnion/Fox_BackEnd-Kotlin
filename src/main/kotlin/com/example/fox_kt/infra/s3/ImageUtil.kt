package com.example.fox_kt.infra.s3

import org.springframework.web.multipart.MultipartFile


interface ImageUtil {
    fun uploadImage(image: MultipartFile): String
}