package com.example.app.approver.ui.controllers

import com.example.app.approver.messaging.MessageService
import com.example.app.approver.ui.models.ApproverRequestModel
import org.example.app.api.dto.VerificationDto
import org.example.app.api.dto.VerificationStatus
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/verifications")
class ApproverController(@Autowired private val msgService: MessageService) {

    @PostMapping
    fun createVerification(@RequestBody userDetails: ApproverRequestModel): ResponseEntity<Void> {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val msg: VerificationDto = modelMapper.map(userDetails, VerificationDto::class.java)
        msg.verificationStatus = VerificationStatus.APPROVED
        msgService.broadcastMessage(msg)
        return ResponseEntity(HttpStatus.CREATED)
    }
}