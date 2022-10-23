package br.com.sisvoli.api.requests

data class PasswordRecoverRequest(
    val cpf: String,
    val token: String,
    val newPassword: String? = null
)
