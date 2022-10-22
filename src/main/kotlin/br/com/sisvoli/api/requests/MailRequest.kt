package br.com.sisvoli.api.requests

class MailRequest(
    val emailTo: String,
    val subject: String,
    val text: String
)
