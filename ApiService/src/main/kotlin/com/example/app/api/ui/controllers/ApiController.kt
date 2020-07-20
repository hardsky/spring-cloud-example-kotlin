package com.example.app.api.ui.controllers

import com.example.app.api.ui.models.SendVerificationRequestModel
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class ApiController(
    @Autowired val env: Environment
) {
    @GetMapping("/status/check")
    fun status(): String = "Working on port: ${env["local.server.port"]}"

    @PostMapping
    fun sendVerification(@Valid @RequestBody details: SendVerificationRequestModel): ResponseEntity<String> {
        val mapper = ModelMapper()
        TODO()
    }
}