package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import br.com.sisvoli.models.UserModel
import br.com.sisvoli.validation.CpfAvailable
import br.com.sisvoli.validation.EmailAvailable
import br.com.sisvoli.validation.UsernameAvailable
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

data class UserRequest(
    @field: NotBlank(message = "O Nome precisa ser informado")
    val name: String,
    @field: NotBlank(message = "O Genero precisa ser informado ")
    val gender: String,
    @field: Email(message = "E-mail deve ser válido")
    @EmailAvailable
    val email: String,
    @field: NotBlank(message = "O Password precisa ser informado")
    val password: String,
    @field: CPF(message = "O CPF precisa ser valido")
    @CpfAvailable
    val cpf: String,
    val phoneNumber: String? = null,
    @field: Past(message = "A data não pode ser futura")
    val birthDate: LocalDate,
    @field: NotBlank(message = "O Username precisa ser informado")
    @UsernameAvailable
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
