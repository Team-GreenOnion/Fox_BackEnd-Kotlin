package com.example.fox_kt.domain.user.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode


object NameAlreadyExistsException : BusinessException (
    ErrorCode.NAME_ALREADY_EXISTS
)