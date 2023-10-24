package com.example.fox_kt.global.config.socket

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.socket.server.standard.ServerEndpointExporter

@Component
class SocketConfig {

    @Bean
    fun  serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }
}