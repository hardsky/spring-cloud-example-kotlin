package com.example.app.api.ui.models

import java.time.LocalDate

data class SendVerificationRequestModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDay: LocalDate,
    val cityLiving: String,
    val cityRegistration: String
)