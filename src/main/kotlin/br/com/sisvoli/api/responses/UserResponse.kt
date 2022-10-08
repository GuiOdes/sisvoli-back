package br.com.sisvoli.api.responses

import br.com.sisvoli.enums.Gender
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class UserResponse(
    val id: UUID? = null,
    val name: String,
    val gender: Gender,
    val email: String,
    val cpf: String,
    val phoneNumber: String? = null,
    val birthDate: LocalDate,
    val username: String,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null,
    val roleName: String
)
