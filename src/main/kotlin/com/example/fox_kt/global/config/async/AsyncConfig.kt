package com.example.fox_kt.global.config.async

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurerSupport() {
    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 50
        executor.maxPoolSize = 300
        executor.setQueueCapacity(500)
        executor.threadNamePrefix = "DDAJA-ASYNC-"
        executor.initialize()
        return executor
    }
}