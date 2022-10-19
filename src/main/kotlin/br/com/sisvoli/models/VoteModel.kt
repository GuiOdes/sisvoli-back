package br.com.sisvoli.models

import java.time.LocalDateTime
import java.util.UUID

class VoteModel(
    val userId: UUID,
    val optionId: UUID,
    val creationDate: LocalDateTime? = null
)
