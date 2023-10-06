package com.example.fox_kt.global.security.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode

object ExpiredTokenException : BusinessException (
    ErrorCode.EXPIRED_TOKEN
)