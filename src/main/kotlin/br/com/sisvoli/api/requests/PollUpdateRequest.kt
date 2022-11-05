package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.enums.PollType
import java.time.LocalDateTime

data class PollUpdateRequest(
    val title: String?,
    val description: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
)