package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import br.com.sisvoli.models.UserModel
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UserRequest(
    @field: NotEmpty(message = "O nome precisa ser informado")
    val name: String,
    @field:NotEmpty(message = "Informe seu sexo")
    val gender: String,
    @field:Email(message = "Digite seu e-mail valido")
    val email: String,
    @field:NotEmpty(message = "Digite uma senha valida")
    val password: String,
    @field:NotEmpty(message = "Digite seu CPF")
    val cpf: String,
    val phoneNumber: String? = null,
    @field:NotEmpty(message = "Digite sua data de nascimento")
    val birthDate: LocalDate,
    @field:NotEmpty(message = "Digite um username unico")
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
