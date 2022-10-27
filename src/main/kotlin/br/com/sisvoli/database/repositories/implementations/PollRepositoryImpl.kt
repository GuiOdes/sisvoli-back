package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.PollEntity
import br.com.sisvoli.database.repositories.interfaces.PollRepository
import br.com.sisvoli.database.repositories.springData.PollSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.PollModel
import org.springframework.stereotype.Component

@Component
class PollRepositoryImpl(
    private val pollSpringDataRepository: PollSpringDataRepository,
    private val userSpringDataRepository: UserSpringDataRepository
): PollRepository{
    override fun save(pollModel: PollModel): PollModel {
        val pollEntity = PollEntity.of(
            pollModel,
            userSpringDataRepository.findById(pollModel.userOwnerId).orElseThrow{UserNotFoundException()}
        )
        return pollSpringDataRepository.save(pollEntity).toPollModel()
    }

    override fun findAll(): MutableList<PollEntity> {
        return pollSpringDataRepository.findAll()
    }

}