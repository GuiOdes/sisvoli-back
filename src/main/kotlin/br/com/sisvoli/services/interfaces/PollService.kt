package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.models.PollModel
import java.util.UUID

interface PollService {
    fun save(pollRequest: PollRequest, userDocument: String): PollModel
    fun findAll(): List<PollModel>
    fun findAllByLoggedUser(userDocument: String): List<PollModel>
    fun findAllByStatus(status: PollStatus): List<PollModel>
    fun findById(pollId: UUID): PollModel
    fun changeStatusById(id: UUID, status: PollStatus): PollModel
    fun findAllScheduledFromToday(): List<PollModel>
    fun findAllPollsToEndToday(): List<PollModel>
    fun cancelById(pollId: UUID)
}
