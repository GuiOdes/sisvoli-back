package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.PollFilters
import br.com.sisvoli.api.requests.PollPageParams
import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.api.requests.PollUpdateRequest
import br.com.sisvoli.api.responses.OptionRankingResponse
import br.com.sisvoli.api.responses.PollRankingResponse
import br.com.sisvoli.database.repositories.interfaces.PollRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotCreatedThePollException
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotUpdateThePollException
import br.com.sisvoli.exceptions.invalid.InvalidDateException
import br.com.sisvoli.exceptions.invalid.InvalidEndDateException
import br.com.sisvoli.exceptions.invalid.InvalidPollCancelRequest
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledException
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledUpdateException
import br.com.sisvoli.models.PollModel
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.services.interfaces.VoteService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PollServiceImpl(
    private val pollRepository: PollRepository,
    private val userService: UserService,
    private val voteService: VoteService
) : PollService {
    override fun save(pollRequest: PollRequest, userDocument: String): PollModel {
        if (isEndDateLargerStartDate(pollRequest.endDate, pollRequest.startDate)) {
            val userId = userService.findByCpf(userDocument).id

            logger.info { "Starting to save a new poll of user $userId" }

            val pollStatus = PollStatus.SCHEDULED
            return pollRepository.save(pollRequest.toPollModel(userId!!, pollStatus))
        } else {
            throw InvalidEndDateException()
        }
    }

    override fun findAllBy(pollPageParams: PollPageParams, filters: PollFilters): Page<PollModel> {
        logger.info { "Starting to list all polls..." }
        return pollRepository.findAllBy(pollPageParams, filters)
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

    override fun cancelById(pollId: UUID) {
        val pollModel = pollRepository.findById(pollId)

        if (pollModel.status != PollStatus.SCHEDULED) {
            throw InvalidPollCancelRequest()
        }

        pollRepository.save(pollModel.copy(status = PollStatus.CANCELED))
    }

    override fun update(pollID: UUID, userDocument: String, pollUpdateRequest: PollUpdateRequest): PollModel {
        val pollModel = pollRepository.findById(pollID)

        if (pollModel.status != PollStatus.SCHEDULED) {
            throw InvalidPollNotScheduledUpdateException()
        }

        val userId = userService.findByCpf(userDocument).id

        if (userId != pollModel.userOwnerId) {
            throw UserLoggedDidNotUpdateThePollException()
        }

        val pollToSave = pollModel.copy(
            title = pollUpdateRequest.title ?: pollModel.title,
            description = pollUpdateRequest.description ?: pollModel.description,
            startDate = pollUpdateRequest.startDate ?: pollModel.startDate,
            endDate = pollUpdateRequest.endDate ?: pollModel.endDate
        )

        if (!isEndDateLargerStartDate(pollToSave.endDate, pollToSave.startDate)) {
            throw InvalidEndDateException()
        }

        if (!isDateLargerThanActual(pollToSave.startDate)) {
            throw InvalidDateException()
        }

        return pollRepository.save(pollToSave)
    }

    override fun getRankingByPollId(pollId: UUID, userViewDocument: String): PollRankingResponse {
        val pollModel = findById(pollId)
        val userModel = userService.findByCpf(userViewDocument)

        if (pollModel.status == PollStatus.SCHEDULED) {
            throw InvalidPollNotScheduledException() // TODO alterar exceção
        }

        val optionRankingResponseList = pollModel.optionList!!.map {
            OptionRankingResponse(
                id = it.id!!,
                name = it.name,
                totalVotes = voteService.countVotesOfOptionById(it.id),
            )
        }

        if (pollModel.status == PollStatus.PROGRESS && userModel.id != pollModel.userOwnerId) {
            throw UserLoggedDidNotCreatedThePollException()
        }

        return PollRankingResponse(
            pollId = pollModel.id!!,
            voteCount = voteService.countVotesOfPollById(pollId),
            optionRanking = optionRankingResponseList
        )
    }

    override fun countVotesById(pollId: UUID): Long {
        return pollRepository.countVotesById(pollId)
    }

    private fun isEndDateLargerStartDate(endDate: LocalDateTime, startDate: LocalDateTime) = endDate > startDate
    private fun isDateLargerThanActual(dateTime: LocalDateTime) = dateTime > LocalDateTime.now()

    companion object {
        val logger = KotlinLogging.logger { }
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}
