package com.example.fox_kt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class FoxKtApplication

fun main(args: Array<String>) {
    runApplication<FoxKtApplication>(*args)
}
