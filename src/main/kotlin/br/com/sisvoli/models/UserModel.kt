package br.com.sisvoli.models

import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.enums.Gender
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class UserModel(
    val id: UUID? = null,
    val name: String,
    val gender: Gender,
    val email: String,
    val password: String,
    val cpf: String,
    val phoneNumber: String? = null,
    val birthDate: LocalDate,
    val username: String,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null,
    val roleName: String
) {
    fun toUserResponse() = UserResponse(
        id = id,
        name = name,
        gender = gender,
        email = email,
        cpf = cpf,
        phoneNumber = phoneNumber,
        birthDate = birthDate,
        username = username,
        creationDate = creationDate,
        updateDate = updateDate,
        roleName = roleName
    )
}
