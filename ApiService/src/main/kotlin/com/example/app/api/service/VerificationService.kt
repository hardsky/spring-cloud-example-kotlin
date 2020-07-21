package com.example.app.api.service

import org.example.app.api.dto.VerificationDto
import org.example.app.api.dto.VerificationStatus

interface VerificationService {
    fun sendVerification(dto: VerificationDto): VerificationDto
    fun checkStatus(id: String): VerificationStatus
    fun updateStatus(dto: VerificationDto)
}