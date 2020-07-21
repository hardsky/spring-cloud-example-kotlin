package com.example.app.api.ui.controllers

import com.example.app.api.service.VerificationService
import com.example.app.api.ui.models.SendVerificationRequestModel
import org.example.app.api.dto.VerificationDto
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController("/api")
class ApiController(
    @Autowired val env: Environment,
    @Autowired val verificationService: VerificationService
) {
    @GetMapping("/status/check")
    fun status(): String = "Working on port: ${env["local.server.port"]}"

    @PostMapping("/send-verification")
    fun sendVerification(@Valid @RequestBody details: SendVerificationRequestModel): ResponseEntity<String> {
        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val dto = mapper.map(details, VerificationDto::class.java)
        val sent = verificationService.sendVerification(dto)

        return ResponseEntity.ok(sent.verificationId)
    }

    @GetMapping("/check-verification/{id}")
    fun checkVerification(@PathVariable("id") id:String): ResponseEntity<String> {
        return ResponseEntity.ok(verificationService.checkStatus(id).toString())
    }
}