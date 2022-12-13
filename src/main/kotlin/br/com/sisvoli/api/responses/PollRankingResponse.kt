package br.com.sisvoli.api.responses

import br.com.sisvoli.models.PollModel

data class PollRankingResponse(
    val pollId: PollModel,
    val voteCount: Long,
    val optionRanking: List<OptionRankingResponse>
)
