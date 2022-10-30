package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.models.PollModel
import java.util.UUID

interface PollRepository {
    fun save(pollModel: PollModel): PollModel
    fun findAll(): List<PollModel>
    fun findAllByLoggedUser(userID: UUID): List<PollModel>
    fun findAllByStatus(status: PollStatus): List<PollModel>
    fun findById(id: UUID): PollModel
    fun findAllScheduledFromToday(): List<PollModel>
    fun findAllPollsToEndToday(): List<PollModel>
}
