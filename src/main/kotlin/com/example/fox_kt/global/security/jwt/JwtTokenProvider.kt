package com.example.fox_kt.global.security.jwt

import com.example.fox_kt.global.security.exception.ExpiredTokenException
import com.example.fox_kt.global.security.exception.InvalidJwtException
import com.example.fox_kt.global.security.jwt.dto.TokenResponse
import com.example.fox_kt.global.security.jwt.entity.RefreshToken
import com.example.fox_kt.global.security.jwt.repository.RefreshTokenRepository
import com.example.fox_kt.global.security.principle.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun getToken(email: String): TokenResponse {
        val accessToken: String = generateToken(email, jwtProperties.accessExp)
        val refreshToken: String = generateRefreshToken(email)
        val currentTimeMillis = System.currentTimeMillis()
        val expirationTime = currentTimeMillis + (jwtProperties.accessExp * 1000)
        return TokenResponse(accessToken = accessToken, refreshToken = refreshToken, expiredAt = expirationTime)
    }

    fun generateRefreshToken(email: String): String {
        val newRefreshToken: String = generateToken(email, jwtProperties.refreshExp)
        val currentTimeMillis = System.currentTimeMillis()
        val expirationTime = currentTimeMillis + (jwtProperties.refreshExp * 1000)
        refreshTokenRepository.save(
            RefreshToken(
                email = email,
                refreshToken = newRefreshToken,
                refreshTokenTime = expirationTime
            )
        )
        return newRefreshToken
    }

    private fun generateToken(email: String, expiration: Long): String {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .setSubject(email)
            .setHeaderParam("typ", "access")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer: String? = request.getHeader("Authorization")

        return parseToken(bearer)
    }

    fun parseToken(bearerToken: String?): String? {
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "")
        } else null
    }

    fun authorization(token: String): UsernamePasswordAuthenticationToken {
        return token.let {
            val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
            return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        }
    }

    private fun getTokenSubject(subject: String): String {
        return getTokenBody(subject).subject
    }

    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secret)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw ExpiredTokenException
                is InvalidClaimException -> throw InvalidJwtException
                else -> throw InvalidJwtException
            }
        }
    }
}