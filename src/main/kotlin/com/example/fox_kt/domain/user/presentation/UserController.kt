package com.example.fox_kt.domain.user.presentation

import com.example.fox_kt.domain.user.presentation.dto.request.FindPasswordWthEmailRequest
import com.example.fox_kt.domain.user.presentation.dto.request.ModifyPasswordRequest
import com.example.fox_kt.domain.user.presentation.dto.request.UserLoginRequest
import com.example.fox_kt.domain.user.presentation.dto.request.UserSignupRequest
import com.example.fox_kt.domain.user.presentation.dto.response.QueryMyInfoResponse
import com.example.fox_kt.domain.user.service.*
import com.example.fox_kt.global.security.jwt.dto.TokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "user", description = "user API")
@RestController
@RequestMapping("/user")
class UserController(
    private val userSignupService: UserSignupService,
    private val userLoginService: UserLoginService,
    private val uploadUserProfileService: UploadUserProfileService,
    private val modifyPasswordService: ModifyPasswordService,
    private val findPasswordWithEmailService: FindPasswordWithEmailService,
    private val queryMyInfoService: QueryMyInfoService
) {

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody userSignupRequest: UserSignupRequest) =
        userSignupService.signup(userSignupRequest)

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest) : TokenResponse =
        userLoginService.login(userLoginRequest)

    @Operation(summary = "프로필 사진 업로드")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profile/upload")
    fun uploadProfile(@RequestPart(required = false, value = "profile") profile : MultipartFile) =
        uploadUserProfileService.uploadProfile(profile)

    @Operation(summary = "비밀번호 수정")
    @PatchMapping("/update/password")
    fun modifyPassword(@RequestBody modifyPasswordRequest: ModifyPasswordRequest) =
        modifyPasswordService.modifyPassword(modifyPasswordRequest)


    @Operation(summary = "비밀번호 찾기")
    @PatchMapping("/new/password")
    fun passwordWithEmail(findPasswordWthEmailRequest: FindPasswordWthEmailRequest) =
        findPasswordWithEmailService.passwordWithEmail(findPasswordWthEmailRequest)


    @Operation(summary = "내 정보 확인")
    @GetMapping("/info")
    fun queryMyInfo() : QueryMyInfoResponse =
            queryMyInfoService.queryMyInfo()
}