package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.Gender
import java.time.LocalDate

data class UserUpdateRequest(
    val name: String? = null,
    val gender: Gender? = null,
    val email: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val birthDate: LocalDate? = null
)
