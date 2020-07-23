package com.example.app.api.data

import org.example.app.api.dto.VerificationDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "id-ws")
interface IdGeneratorServiceClient {
    @PostMapping("/ids")
    fun createId(): String
}
