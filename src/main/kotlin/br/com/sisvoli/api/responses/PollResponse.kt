package br.com.sisvoli.api.responses

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.enums.PollType
import java.time.LocalDateTime
import java.util.UUID

data class PollResponse(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val type: PollType,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val status: PollStatus,
    val userOwnerId: UUID
)
