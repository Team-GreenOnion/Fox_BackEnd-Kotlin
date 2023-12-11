package com.example.fox_kt.global.error.exception

enum class ErrorCode (
    val status: Int,
    val message: String
){
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    INVALID_TOKEN(401,"Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    EMAIL_CODE_MISS_MATCH(400, "Email Code Miss Match"),
    EMAIL_NOT_FOUND(404, "Email Not Found"),
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),

    PASSWORD_MISS_MATCH(400, "Password Miss Match"),
    USER_NOT_FOUND(404, "User Not Found"),
    NAME_ALREADY_EXISTS(409, "Name Already Exists"),

    SCHOOL_NOT_FOUND(404, "School Not Found"),

    SELECT_1_OR_MORE_AND_3_OR_LESS(401, "Select 1 or More And 3 or Less"),

    OPEN_CHAT_ROOM_NOT_FOUND(404, "Open Chat Not Found")
}