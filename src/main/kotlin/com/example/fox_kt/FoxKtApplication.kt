package com.example.fox_kt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableFeignClients
@EnableAsync
class FoxKtApplication

fun main(args: Array<String>) {
    runApplication<FoxKtApplication>(*args)
}
