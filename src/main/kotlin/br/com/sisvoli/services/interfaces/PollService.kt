package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.PollFilters
import br.com.sisvoli.api.requests.PollPageParams
import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.api.requests.PollUpdateRequest
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.models.PollModel
import org.springframework.data.domain.Page
import java.util.UUID

interface PollService {
    fun save(pollRequest: PollRequest, userDocument: String): PollModel
    fun findAllBy(pollPageParams: PollPageParams, filters: PollFilters): Page<PollModel>
    fun findAllByLoggedUser(userDocument: String): List<PollModel>
    fun findAllByStatus(status: PollStatus): List<PollModel>
    fun findById(pollId: UUID): PollModel
    fun changeStatusById(id: UUID, status: PollStatus): PollModel
    fun findAllScheduledFromToday(): List<PollModel>
    fun findAllPollsToEndToday(): List<PollModel>
    fun cancelById(pollId: UUID)
    fun update(pollID: UUID, userDocument: String, pollUpdateRequest: PollUpdateRequest): PollModel
}
