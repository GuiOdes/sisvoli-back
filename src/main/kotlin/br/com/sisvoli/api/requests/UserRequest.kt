package br.com.sisvoli.api.requests

import java.time.LocalDate

data class UserRequest(
    val name: String,
    val gender: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phoneNumber: String? = null,
    val birthDate: LocalDate,
    val username: String
)
