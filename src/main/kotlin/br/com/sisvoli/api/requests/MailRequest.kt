package br.com.sisvoli.api.requests

class MailRequest(
    val ownerRef: String,
    val emailFrom: String,
    val emailTo: String,
    val subject: String,
    val text: String
)
