package com.example.app.api.data

import feign.FeignException
import feign.hystrix.FallbackFactory
import org.example.app.api.dto.VerificationDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "approver-ws", fallbackFactory = ApproverFallbackFactory::class)
interface ApproverServiceClient {
    @PostMapping("/verifications")
    fun createVerification(@RequestBody verification: VerificationDto)
}

@Component
internal class ApproverFallbackFactory : FallbackFactory<ApproverServiceClient> {
    override fun create(cause: Throwable): ApproverServiceClient {
        return ApproverServiceClientFallback(cause)
    }
}

internal class ApproverServiceClientFallback(private val cause: Throwable) : ApproverServiceClient {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun createVerification(verification: VerificationDto) {
        if (cause is FeignException && cause.status() == 404) {
            logger.error("404 error took place when createVerification was called with verificationId: ${verification.verificationId}"
                    + ". Error message: ${cause.localizedMessage}")
        } else {
            logger.error("Other error took place: ${cause.localizedMessage}")
        }
    }

}