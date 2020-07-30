package com.example.app.idgen.ui.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class IdController @Autowired constructor(private val env: Environment) {
    @GetMapping("/status/check")
    fun status(): String = "Working on port: ${env["local.server.port"]}"

    @PostMapping("/ids")
    fun createId(): ResponseEntity<String>{
        val id = UUID.randomUUID().toString()
        return ResponseEntity.ok(id)
    }
}
