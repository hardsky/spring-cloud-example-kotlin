package com.example.app.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
class ApiZuulGatewayApplication

fun main(args: Array<String>) {
	runApplication<ApiZuulGatewayApplication>(*args)
}
