package br.com.sisvoli.api.responses

import java.util.UUID

data class OptionRankingResponse(
    val id: UUID,
    val name: String,
    val totalVotes: Long
)
