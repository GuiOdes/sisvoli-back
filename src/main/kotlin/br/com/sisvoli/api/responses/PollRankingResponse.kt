package br.com.sisvoli.api.responses

import java.util.UUID

data class PollRankingResponse(
    val pollId: UUID,
    val voteCount: Long,
    val optionRanking: List<OptionRankingResponse>
)
