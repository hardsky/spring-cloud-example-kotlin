package com.example.app.approver.messaging

import org.example.app.api.dto.VerificationDto

interface MessageService {
    fun broadcastMessage(msg: VerificationDto)
}