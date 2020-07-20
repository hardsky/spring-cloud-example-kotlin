package com.example.app.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import feign.Logger

@SpringBootApplication
class ApiServiceApplication {

    val feignLoggerLevel = Logger.Level.FULL
}

fun main(args: Array<String>) {
    runApplication<ApiServiceApplication>(*args)
}
