package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.OptionModel
import java.util.UUID

interface OptionRepository {
    fun save(optionModel: OptionModel, pollID: UUID): OptionModel
    fun existsByNameAndPollEntityId(name: String, pollId: UUID): Boolean
    fun findAllByPollId(pollId: UUID): List<OptionModel>
    fun deleteById(optionId: UUID, loggedUserDocument: String)
    fun findById(optionId: UUID): OptionModel
    fun countVotesById(optionId: UUID): Long
}
