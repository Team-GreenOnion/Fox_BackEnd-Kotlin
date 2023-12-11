package com.example.fox_kt.global.config.swagger

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.info.Info;

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("'정 동프' API")
                    .description("'정 동프' API 명세서입니다.")
                    .version("v1")
            )
    }
}