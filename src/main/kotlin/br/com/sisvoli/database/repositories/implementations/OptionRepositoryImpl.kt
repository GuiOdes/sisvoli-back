package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.OptionEntity
import br.com.sisvoli.database.entities.PollEntity
import br.com.sisvoli.database.repositories.interfaces.OptionRepository
import br.com.sisvoli.database.repositories.springData.OptionSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.OptionModel
import br.com.sisvoli.services.interfaces.PollService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OptionRepositoryImpl(
    private val optionSpringDataRepository: OptionSpringDataRepository,
    private val userSpringDataRepository: UserSpringDataRepository,
    private val pollService: PollService

) : OptionRepository {
    override fun save(optionModel: OptionModel, pollID: UUID): OptionModel {
        val pollEntity = PollEntity.of(
            pollService.findById(pollID),
            userSpringDataRepository
                .findById(pollService.findById(pollID).userOwnerId)
                .orElseThrow { UserNotFoundException() }
        )
        val optionEntity = OptionEntity.of(optionModel, pollEntity)
        return optionSpringDataRepository.save(optionEntity).toOptionModel()
    }

    override fun existsByNameAndPollEntityId(name: String, pollId: UUID): Boolean {
        return optionSpringDataRepository.existsByNameAndPollEntityId(name, pollId)
    }
}
