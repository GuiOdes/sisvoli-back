package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.OptionRequest
import br.com.sisvoli.database.repositories.interfaces.OptionRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotCreatedThePollException
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledException
import br.com.sisvoli.models.OptionModel
import br.com.sisvoli.services.interfaces.OptionService
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
class OptionServiceImpl(
    private val optionRepository: OptionRepository,
    private val pollService: PollService,
    private val userService: UserService
) : OptionService {
    @Transactional
    override fun save(optionRequest: OptionRequest, userDocument: String): List<OptionModel> {
        val userID = userService.findByCpf(userDocument).id
        val pollModel = pollService.findById(optionRequest.pollId)
        val optionList = mutableListOf<OptionModel>()

        if (pollModel.status != PollStatus.SCHEDULED) {
            throw InvalidPollNotScheduledException()
        }

        if (userID != pollModel.userOwnerId) {
            throw UserLoggedDidNotCreatedThePollException()
        }

        optionRepository.deleteAllByPollId(optionRequest.pollId)

        optionRequest.optionsName.forEach {
            if (!existsByNameAndPollId(it, pollModel.id!!) && it.isNotBlank()) {
                optionList.add(
                    optionRepository.save(
                        OptionModel(
                            id = null,
                            it.trim(),
                            optionRequest.pollId
                        )
                    )
                )
            }
        }

        return optionList
    }

    override fun existsByNameAndPollId(name: String, pollId: UUID): Boolean {
        return optionRepository.existsByNameAndPollEntityId(name, pollId)
    }

    override fun findAllByPollId(pollId: UUID): List<OptionModel> {
        return optionRepository.findAllByPollId(pollId)
    }

    override fun deleteById(optionId: UUID, loggedUserDocument: String) {
        return optionRepository.deleteById(optionId, loggedUserDocument)
    }

    override fun findById(optionId: UUID): OptionModel {
        return optionRepository.findById(optionId)
    }

    override fun countVotesById(optionId: UUID): Long {
        return optionRepository.countVotesById(optionId)
    }
}
