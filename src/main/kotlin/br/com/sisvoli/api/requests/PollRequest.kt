package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.enums.PollType
import br.com.sisvoli.models.PollModel
import java.time.LocalDateTime
import java.util.UUID

data class PollRequest(
    val title: String,
    val description: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
    fun toPollModel(userID: UUID, pollStatus: PollStatus): PollModel {
        return PollModel(
            title = title,
            description = description,
            type = PollType.DEFAULT,
            startDate = startDate,
            endDate = endDate,
            status = pollStatus,
            userOwnerId = userID
        )
    }
}
