package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.repositories.interfaces.VoteRepository
import br.com.sisvoli.database.repositories.springData.OptionSpringDataRepository
import br.com.sisvoli.database.repositories.springData.VoteSpringDataRepository
import br.com.sisvoli.exceptions.conflict.VoteAlreadyExistsException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class VoteRepositoryImpl(
    val voteSpringDataRepository: VoteSpringDataRepository,
    val optionSpringDataRepository: OptionSpringDataRepository
) : VoteRepository {
    override fun addVote(userId: UUID, optionId: UUID) {
        val pollId = optionSpringDataRepository.findById(optionId).get().pollEntity.id!!

        if (voteSpringDataRepository.existsByOptionEntityPollEntityIdAndUserEntityId(pollId, userId)) {
            throw VoteAlreadyExistsException()
        }

        voteSpringDataRepository.save(userId, optionId)
    }
}
