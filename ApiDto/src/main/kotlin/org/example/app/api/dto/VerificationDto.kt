package org.example.app.api.dto

import java.io.Serializable
import java.time.LocalDate

data class VerificationDto(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var birthDay: LocalDate = LocalDate.now(),
    var cityLiving: String = "",
    var cityRegistration: String = "",
    var verificationId: String = "",
    var verificationStatus: VerificationStatus = VerificationStatus.PENDING
): Serializable