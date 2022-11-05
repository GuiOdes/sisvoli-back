package br.com.sisvoli.api.requests

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.enums.PollType
import br.com.sisvoli.models.PollModel
import java.time.LocalDateTime
import java.util.UUID
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank

data class PollRequest(
    @field:NotBlank(message = "O titulo da enquete precisa ser informado")
    val title: String,
    @field:NotBlank(message = "A descrição da enquete precisa ser informada")
    val description: String,
    @field:Future(message = "A data precisa ser posterior a hoje")
    val startDate: LocalDateTime,
    @field:Future(message = "A data precisa ser posterior a hoje")
    val endDate: LocalDateTime
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
