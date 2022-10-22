package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.MailRequest
import br.com.sisvoli.services.interfaces.EmailService
import mu.KotlinLogging
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender
) : EmailService {
    val emailFrom = "sisvoli.app@gmail.com"

    override fun sendMail(mailRequest: MailRequest) {
        val message = SimpleMailMessage()
        message.setFrom(emailFrom)
        message.setTo(mailRequest.emailTo)
        message.setSubject(mailRequest.subject)
        message.setText(mailRequest.text)
        mailSender.send(message)
    }

    companion object {
        val logger = KotlinLogging.logger { }
    }
}
