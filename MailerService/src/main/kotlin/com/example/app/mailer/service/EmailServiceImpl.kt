package com.example.app.mailer.service

import org.example.app.api.dto.VerificationDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl : EmailService {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun sendEmail(dto: VerificationDto) {
        logger.info("email with status ${dto.verificationStatus} is sent to address ${dto.email}")
        // TODO("Not yet implemented")
    }

//	@Autowired
    //	private JavaMailSender emailSender;
    //	
    //	@Override
    //	public void sendEmail(String to, String subject, String text) {
    //        SimpleMailMessage message = new SimpleMailMessage();
    //        message.setFrom("noreply@test.test");
    //        message.setTo(to);
    //        message.setSubject(subject);
    //        message.setText(text);
    //
    //        emailSender.send(message);
    //	}
}
