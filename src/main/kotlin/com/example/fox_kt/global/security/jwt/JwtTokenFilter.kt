package com.example.fox_kt.global.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter (
    private val jwtProvider: JwtProvider
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token : String? = jwtProvider.resolveToken(request)
        if (token != null) {
            val authentication : Authentication = jwtProvider.authentication(token)
            SecurityContextHolder.getContext().authentication
        }
        filterChain.doFilter(request, response)
    }
}