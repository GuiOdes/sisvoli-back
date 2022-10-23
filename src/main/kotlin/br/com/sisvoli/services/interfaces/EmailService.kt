package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.MailRequest

interface EmailService {
    fun sendMail(mailRequest: MailRequest)
}
