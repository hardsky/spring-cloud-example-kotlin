package com.example.app.approver.messaging

import org.example.app.api.dto.VerificationDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl @Autowired constructor(private val rabbitTemplate: RabbitTemplate, private val env: Environment) : MessageService {
    override fun broadcastMessage(msg: VerificationDto) {
        rabbitTemplate.convertAndSend(env.getProperty("api.messaging.topic-exchange-name")!!,
                env.getProperty("api.messaging.routing.key")!!, msg)
    }

}