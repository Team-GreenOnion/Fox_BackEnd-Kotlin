package com.example.fox_kt.domain.user.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode


object EmailCodeMissMatchException : BusinessException(
    ErrorCode.EMAIL_CODE_MISS_MATCH
)