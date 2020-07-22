package com.example.app.approver.ui.models

import java.time.LocalDate

data class ApproverRequestModel(
        val firstName: String,
        val lastName: String,
        val email: String,
        val birthDay: LocalDate,
        val cityLiving: String,
        val cityRegistration: String,
        val verificationId: String
)