package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import br.com.sisvoli.models.UserModel
import br.com.sisvoli.validation.EmailAvailable
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Past

data class UserRequest(
    @field: NotEmpty(message = "O Nome precisa ser informado")
    val name: String,
    @field: NotEmpty(message = "O Genero precisa ser informado ")
    val gender: String,
    @field: Email(message = "E-mail deve ser válido")
    @EmailAvailable
    val email: String,
    @field: NotEmpty(message = "O Password precisa ser informado")
    val password: String,
    @field: NotEmpty(message = "O CPF precisa ser informado")
    val cpf: String,
    val phoneNumber: String? = null,
    @field: Past
    val birthDate: LocalDate,
    @field: NotEmpty(message = "O Username precisa ser informado")
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
