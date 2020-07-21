package com.example.app.api.data

import org.springframework.data.repository.CrudRepository

interface VerificationRepository : CrudRepository<VerificationEntity, Long> {
    fun findByVerificationId(verificationId: String): VerificationEntity?
}