package com.example.app.api.service

import com.example.app.api.data.ApproverServiceClient
import com.example.app.api.data.IdGeneratorServiceClient
import com.example.app.api.data.VerificationEntity
import com.example.app.api.data.VerificationRepository
import org.example.app.api.dto.VerificationDto
import org.example.app.api.dto.VerificationStatus
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class VerificationServiceImpl @Autowired constructor(
        private val repository: VerificationRepository,
        private val approver: ApproverServiceClient,
        private val idGenerator: IdGeneratorServiceClient
): VerificationService {

    private val mapper = ModelMapper().apply {
        configuration.matchingStrategy = MatchingStrategies.STRICT
    }

    override fun sendVerification(dto: VerificationDto): VerificationDto {
        val sent = sendToApprover(dto)
        return saveToDb(sent)
    }

    override fun checkStatus(id: String): VerificationStatus {
        val entity = repository.findByVerificationId(id)
        return entity?.status?: VerificationStatus.NONE
    }

    override fun updateStatus(dto: VerificationDto) {
        val entity = repository.findByVerificationId(dto.verificationId)
        entity?.let {
            entity.status = dto.verificationStatus
            repository.save(entity)
        }
    }

    private fun sendToApprover(dto: VerificationDto): VerificationDto{
        dto.verificationId = idGenerator.createId()
        approver.createVerification(dto)
        return dto
    }

    private fun saveToDb(dto: VerificationDto): VerificationDto{
        val created = repository.save(mapper.map(dto, VerificationEntity::class.java))
        return mapper.map(created, VerificationDto::class.java)
    }
}