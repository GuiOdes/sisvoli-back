package br.com.sisvoli.models

import java.time.LocalDateTime
import java.util.UUID

class PasswordRecoveryTokenModel(
    val id: UUID? = null,
    val token: String,
    val userId: UUID,
    val creationDate: LocalDateTime? = null
)
