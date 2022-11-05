package br.com.sisvoli.api.requests

import java.time.LocalDateTime

data class PollUpdateRequest(
    val title: String?,
    val description: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
)
