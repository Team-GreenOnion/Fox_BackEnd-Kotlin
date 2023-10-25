package com.example.fox_kt.global.config.filter

import com.example.fox_kt.global.error.GlobalExceptionFilter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

@Configuration
class FilterConfig (
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>(){
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(GlobalExceptionFilter(objectMapper), AuthorizationFilter::class.java)
    }
}
