package com.example.fox_kt.global.security.jwt

import com.example.fox_kt.domain.user.domain.User
import com.example.fox_kt.global.security.exception.ExpiredTokenException
import com.example.fox_kt.global.security.jwt.dto.TokenResponse
import com.example.fox_kt.global.security.jwt.entity.RefreshToken
import com.example.fox_kt.global.security.jwt.repository.RefreshTokenRepository
import com.example.fox_kt.global.security.principle.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider(
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository,

    @Value("\${spring.jwt.key}")
    private var key: String,

    @Value("\${spring.jwt.live.accessToken}")
    private val accessTokenTime: Long,

    @Value("\${spring.jwt.live.refreshToken}")
    private val refreshTokenTime: Long) {

    init {
        key = Base64.getEncoder().encodeToString(key.toByteArray())
    }

    fun getToken(user: User): TokenResponse {
        val accessToken = generateAccessToken(user.email)
        val refreshToken = generateRefreshToken(user.email)
        return TokenResponse(accessToken, refreshToken, accessTokenTime)
    }

    fun generateAccessToken(email: String): String {
        return generateJwt(email, "access", accessTokenTime)
    }

    fun generateRefreshToken(email: String): String {
        val refreshToken = generateJwt(email, "refresh", refreshTokenTime)
        val refreshTokenEntity = RefreshToken(
            email = email,
            refreshToken = refreshToken,
            refreshTokenTime = refreshTokenTime,
        )
        refreshTokenRepository.save(refreshTokenEntity)
        return refreshToken
    }

    private fun generateKey(): ByteArray {
        return Base64.getDecoder().decode(key.toByteArray())
    }

    private fun generateExpirationDate(expirationMillis: Long): Date {
        return Date(System.currentTimeMillis() + expirationMillis * 1000)
    }

    // 수정된 함수명과 파라미터로 변경
    private fun generateJwt(email:String, type:String, expirationMillis :Long):String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256,generateKey())
            .setSubject(email)
            .claim("type",type)
            .setIssuedAt(Date())
            .setExpiration(generateExpirationDate(expirationMillis))
            .compact()
    }


    fun resolveToken(request: HttpServletRequest?): String? {
        val bearer = request!!.getHeader(HEADER)
        return parseToken(bearer)
    }

    fun authentication(token: String?): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun parseToken(bearerToken: String?): String? =
        if (bearerToken != null && bearerToken.startsWith(PREFIX)) bearerToken.replace(PREFIX, "") else null


    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(key.toByteArray())
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            throw ExpiredTokenException
        }
    }

    private fun getTokenSubject(token: String?): String =
        getTokenBody(token).subject

    companion object {
        private const val HEADER = "Authorization"
        private const val PREFIX = "Bearer"
    }
}
