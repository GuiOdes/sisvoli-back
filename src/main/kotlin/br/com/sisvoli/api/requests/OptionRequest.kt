package br.com.sisvoli.api.requests

import br.com.sisvoli.models.OptionModel
import java.util.UUID
import javax.validation.constraints.NotBlank

data class OptionRequest(
    @field: NotBlank(message = "Digite o nome da opção")
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
