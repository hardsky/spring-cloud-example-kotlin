package com.example.app.mailer.service

import org.example.app.api.dto.VerificationDto

interface EmailService {
    fun sendEmail(dto: VerificationDto)
}