package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.OptionRequest
import br.com.sisvoli.models.OptionModel
import java.util.UUID

interface OptionService {
    fun save(optionRequest: OptionRequest, userDocument: String): OptionModel
    fun existsByNameAndPollId(name: String, pollId: UUID): Boolean
    fun findAllByPollId(pollId: UUID): List<OptionModel>
}
