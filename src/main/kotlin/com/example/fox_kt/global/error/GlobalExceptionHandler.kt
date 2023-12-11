package com.example.fox_kt.global.error

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorResponse
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handlingBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        val errorResponse = ErrorResponse(code.status, code.message, LocalDateTime.now()) // ErrorResponse 객체 생성
        return ResponseEntity(errorResponse, HttpStatus.valueOf(code.status))
    }

    @ExceptionHandler(FeignException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun feignExceptionHandler(ex: FeignException): String {
        ex.printStackTrace()
        return ex.message ?: ""
    }
}