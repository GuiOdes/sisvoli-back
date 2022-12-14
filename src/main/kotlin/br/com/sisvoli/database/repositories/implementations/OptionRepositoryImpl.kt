package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.OptionEntity
import br.com.sisvoli.database.entities.PollEntity
import br.com.sisvoli.database.repositories.interfaces.OptionRepository
import br.com.sisvoli.database.repositories.springData.OptionSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotCreatedThePollException
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledException
import br.com.sisvoli.exceptions.notFound.OptionNotFoundException
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

    override fun findAllByPollId(pollId: UUID): List<OptionModel> {
        return optionSpringDataRepository.findAllByPollEntityId(pollId)
            .map { it.toOptionModel() }
    }

    override fun deleteById(optionId: UUID, loggedUserDocument: String) {
        val optionEntity = optionSpringDataRepository.findById(optionId)
            .orElseThrow { OptionNotFoundException() }

        val pollEntity = optionEntity.pollEntity

        if (pollEntity.userOwner.cpf != loggedUserDocument) {
            throw UserLoggedDidNotCreatedThePollException()
        }

        if (pollEntity.status != PollStatus.SCHEDULED) {
            throw InvalidPollNotScheduledException()
        }

        optionSpringDataRepository.deleteById(optionId)
    }

    override fun findById(optionId: UUID): OptionModel {
        return optionSpringDataRepository.findById(optionId)
            .orElseThrow { OptionNotFoundException() }.toOptionModel()
    }
}
