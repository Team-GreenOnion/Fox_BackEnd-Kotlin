package com.example.fox_kt.global.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
            .allowedOrigins("*")
            .allowedHeaders("*");
    }
}