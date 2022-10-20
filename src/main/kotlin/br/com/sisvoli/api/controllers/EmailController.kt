package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.MailRequest
import br.com.sisvoli.services.interfaces.EmailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController(
    val emailService: EmailService
) {

    @PostMapping
    fun test() {
        emailService.sendMail(
            MailRequest(
                ownerRef = "testeOnly",
                emailFrom = "sisvoli.app@gmail.com",
                emailTo = "hadrianrabelo@hotmail.com",
                subject = "teste de email",
                text = "Estou apenas testando essa bomba"
            )
        )
    }
}
