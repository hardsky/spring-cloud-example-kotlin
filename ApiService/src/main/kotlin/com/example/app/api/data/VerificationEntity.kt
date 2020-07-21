package com.example.app.api.data

import org.example.app.api.dto.VerificationStatus
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "verifications")
class VerificationEntity : Serializable {
    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(nullable = false, length = 150)
    var firstName: String? = null

    @Column(nullable = false, length = 150)
    var lastName: String? = null

    @Column(nullable = false, length = 150, unique = true)
    var email: String? = null

    @Column(nullable = false)
    var birthDay: LocalDate? = null

    @Column(nullable = false, length = 150)
    var cityLiving: String? = null

    @Column(nullable = false, length = 150)
    var cityRegistration: String? = null

    @Column(nullable = false, unique = true)
    var verificationId: String? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    var status: VerificationStatus = VerificationStatus.NONE

    companion object {
        private const val serialVersionUID = 7197111630123472067L
    }
}