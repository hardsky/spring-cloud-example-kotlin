package com.example.app.mailer.messaging

import com.example.app.mailer.service.EmailService
import org.example.app.api.dto.VerificationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Receiver @Autowired constructor(private val emailService: EmailService
) {
    fun receiveMessage(dto: VerificationDto) {
        emailService.sendEmail(dto)
    }
}