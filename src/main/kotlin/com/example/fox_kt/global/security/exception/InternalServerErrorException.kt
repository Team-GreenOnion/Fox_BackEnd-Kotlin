package com.example.fox_kt.global.security.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode

object InternalServerErrorException : BusinessException(
    ErrorCode.INTERNAL_SERVER_ERROR
)