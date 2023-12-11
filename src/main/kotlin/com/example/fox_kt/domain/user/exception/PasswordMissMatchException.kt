package com.example.fox_kt.domain.user.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode


object PasswordMissMatchException : BusinessException (
    ErrorCode.PASSWORD_MISS_MATCH
)