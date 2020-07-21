package com.example.app.api.messaging

import com.example.app.api.service.VerificationService
import org.example.app.api.dto.VerificationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VerificationReceiver constructor(
        @Autowired val verificationService: VerificationService
) {
    fun receiveVerification(msg: VerificationDto) {
        verificationService.updateStatus(msg)
    }

}