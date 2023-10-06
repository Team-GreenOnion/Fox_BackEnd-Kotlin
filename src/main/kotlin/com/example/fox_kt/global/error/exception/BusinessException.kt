package com.example.fox_kt.global.error.exception

abstract class BusinessException (
    val errorCode : ErrorCode
) : RuntimeException()