package com.example.fox_kt.domain.interest.exception

import com.example.fox_kt.global.error.exception.BusinessException
import com.example.fox_kt.global.error.exception.ErrorCode

object Select1orMoreAnd3orLess : BusinessException (
    ErrorCode.SELECT_1_OR_MORE_AND_3_OR_LESS
)