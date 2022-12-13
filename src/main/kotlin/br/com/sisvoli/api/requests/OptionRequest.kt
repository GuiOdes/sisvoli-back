package br.com.sisvoli.api.requests

import br.com.sisvoli.models.OptionModel
import java.util.UUID
import javax.validation.constraints.NotEmpty

data class OptionRequest(
    @field: NotEmpty(message = "Você precisa enviar ao menos uma opção")
    val optionsName: List<String>,
    val pollId: UUID
) {
    fun toOptionModelList(): List<OptionModel> {
        return optionsName.map {
            OptionModel(
                name = it,
                pollId = pollId
            )
        }
    }
}
