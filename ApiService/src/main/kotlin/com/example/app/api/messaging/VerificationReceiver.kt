package com.example.app.api.messaging

import com.example.app.api.service.VerificationService
import org.example.app.api.dto.VerificationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class VerificationReceiver @Autowired constructor(private val verificationService: VerificationService) {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun receiveVerification(dto: VerificationDto) {
        logger.info("verification for email ${dto.email} with status ${dto.verificationStatus}")

        verificationService.updateStatus(dto)
    }

}
