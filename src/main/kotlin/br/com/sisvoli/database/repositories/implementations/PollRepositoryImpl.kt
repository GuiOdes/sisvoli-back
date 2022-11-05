package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.PollEntity
import br.com.sisvoli.database.repositories.interfaces.PollRepository
import br.com.sisvoli.database.repositories.springData.PollSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.exceptions.notFound.PollNotFoundException
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.PollModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PollRepositoryImpl(
    private val pollSpringDataRepository: PollSpringDataRepository,
    private val userSpringDataRepository: UserSpringDataRepository
) : PollRepository {
    override fun save(pollModel: PollModel): PollModel {
        val pollEntity = PollEntity.of(
            pollModel,
            userSpringDataRepository.findById(pollModel.userOwnerId).orElseThrow { UserNotFoundException() }
        )
        return pollSpringDataRepository.save(pollEntity).toPollModel()
    }

    override fun findAll(): List<PollModel> {
        return pollSpringDataRepository.findAll().map { it.toPollModel() }
    }

    override fun findAllByLoggedUser(userOwnerId: UUID): List<PollModel> {
        return pollSpringDataRepository.findAllByUserOwnerId(userOwnerId).map { it.toPollModel() }
    }

    override fun findAllByStatus(status: PollStatus): List<PollModel> {
        return pollSpringDataRepository.findAllByStatus(status).map { it.toPollModel() }
    }

    override fun findById(id: UUID): PollModel {
        return pollSpringDataRepository.findById(id).orElseThrow { PollNotFoundException() }.toPollModel()
    }

    override fun findAllScheduledFromToday(): List<PollModel> {
        return pollSpringDataRepository.findAllScheduledPollsFromToday()
            .map { it.toPollModel() }
    }

    override fun findAllPollsToEndToday(): List<PollModel> {
        return pollSpringDataRepository.findAllPollsToEndToday()
            .map { it.toPollModel() }
    }
}
