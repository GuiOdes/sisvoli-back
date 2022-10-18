package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import java.time.LocalDate

data class UserUpdateRequest(
    val name: String?,
    val gender: Gender?,
    val email: String?,
    val password: String?,
    val phoneNumber: String?,
    val birthDate: LocalDate?
)
