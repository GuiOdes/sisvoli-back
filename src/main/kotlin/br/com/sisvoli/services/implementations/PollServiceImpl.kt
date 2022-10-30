package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.database.repositories.interfaces.PollRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.exceptions.invalid.InvalidEndDateException
import br.com.sisvoli.exceptions.invalid.InvalidPollCancelRequest
import br.com.sisvoli.models.PollModel
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.services.interfaces.UserService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Service
class PollServiceImpl(
    private val pollRepository: PollRepository,
    private val userService: UserService
) : PollService {
    override fun save(pollRequest: PollRequest, userDocument: String): PollModel {
        if (endDateLargerstartDate(pollRequest.endDate, pollRequest.startDate)) {
            val userId = userService.findByCpf(userDocument).id

            logger.info { "Starting to save a new poll of user $userId" }

            val pollStatus = if (pollRequest.startDate > LocalDateTime.now()) {
                PollStatus.SCHEDULED
            } else {
                PollStatus.PROGRESS
            }
            val pollModel = pollRequest.toPollModel(userId!!, pollStatus)
            return pollRepository.save(pollModel)
        } else {
            throw InvalidEndDateException()
        }
    }

    override fun findAll(): List<PollModel> {
        logger.info { "Starting to list all polls..." }
        return pollRepository.findAll()
    }

    override fun findAllByLoggedUser(userDocument: String): List<PollModel> {
        val userID = userService.findByCpf(userDocument).id

        logger.info { "Starting to find all polls from user #$userID..." }

        return pollRepository.findAllByLoggedUser(userID!!)
    }

    override fun findAllByStatus(status: PollStatus): List<PollModel> {
        logger.info { "Starting to find all polls from status ${status.name}..." }
        return pollRepository.findAllByStatus(status)
    }

    override fun findById(pollId: UUID): PollModel {
        return pollRepository.findById(pollId)
    }

    override fun changeStatusById(id: UUID, status: PollStatus): PollModel {
        logger.info { "Starting to change status of poll #$id to ${status.name}..." }
        val pollModel = pollRepository.findById(id)
        return pollRepository.save(pollModel.copy(status = status))
    }

    override fun findAllScheduledFromToday(): List<PollModel> {
        logger.info { "Finding all polls scheduled from day ${LocalDate.now().format(dateFormatter)}..." }
        return pollRepository.findAllScheduledFromToday()
    }

    override fun findAllPollsToEndToday(): List<PollModel> {
        logger.info { "Finding all polls to end on day ${LocalDate.now().format(dateFormatter)}..." }
        return pollRepository.findAllPollsToEndToday()
    }

    companion object {
        val logger = KotlinLogging.logger { }
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    override fun cancelById(pollId: UUID) {
        val pollModel = pollRepository.findById(pollId)

        if (pollModel.status != PollStatus.SCHEDULED) {
            throw InvalidPollCancelRequest()
        }

        pollRepository.save(pollModel.copy(status = PollStatus.CANCELED))
    }
    private fun endDateLargerstartDate(endDate: LocalDateTime, startDate: LocalDateTime): Boolean {
        return endDate > startDate
    }
}
