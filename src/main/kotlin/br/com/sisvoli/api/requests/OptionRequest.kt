package br.com.sisvoli.api.requests

import br.com.sisvoli.models.OptionModel
import java.util.UUID
import javax.validation.constraints.NotBlank

data class OptionRequest(
    @field: NotBlank(message = "Digite o nome da opção")
    val name: String,
    @field: NotBlank(message = "Informe a qual enquete essa opção ira pertencer")
    val pollId: UUID
) {
    fun toOptionModel(): OptionModel {
        return OptionModel(
            name = name,
            pollId = pollId
        )
    }
}
