package com.example.app.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiZuulGatewayApplication

fun main(args: Array<String>) {
	runApplication<ApiZuulGatewayApplication>(*args)
}
