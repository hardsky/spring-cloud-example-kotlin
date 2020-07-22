package com.example.app.idgen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdGeneratorServiceApplication

fun main(args: Array<String>) {
	runApplication<IdGeneratorServiceApplication>(*args)
}
