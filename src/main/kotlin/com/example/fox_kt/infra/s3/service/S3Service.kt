package com.example.fox_kt.infra.s3.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class S3Service(
    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
    private val amazonS3: AmazonS3
) {
    fun uploadImage(image: MultipartFile): String {
        val fileName = UUID.randomUUID().toString() + image.originalFilename
        val request = PutObjectRequest(
            bucketName, fileName, image.inputStream, getObjectMetadata(image)
        ).withCannedAcl(CannedAccessControlList.PublicRead)
        amazonS3.putObject(request)
        return getFileUrl(fileName)
    }

    private fun getObjectMetadata(image: MultipartFile): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = image.size
        objectMetadata.contentType = image.contentType
        return objectMetadata
    }

    fun getFileUrl(fileName: String): String {
        return amazonS3.getUrl(bucketName, fileName).toString()
    }
}
