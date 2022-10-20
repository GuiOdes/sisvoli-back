package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import br.com.sisvoli.models.UserModel
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
) {
    fun toUserModel(roleName: String): UserModel {
        return UserModel(
            name = name,
            gender = Gender.valueOf(gender),
            email = email,
            password = password,
            cpf = cpf,
            phoneNumber = phoneNumber,
            birthDate = birthDate,
            username = username,
            roleName = roleName
        )
    }
}
