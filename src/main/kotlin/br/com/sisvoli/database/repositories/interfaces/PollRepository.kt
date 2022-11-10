package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.api.requests.PollFilters
import br.com.sisvoli.api.requests.PollPageParams
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.models.PollModel
import org.springframework.data.domain.Page
import java.util.UUID

interface PollRepository {
    fun save(pollModel: PollModel): PollModel
    fun findAllBy(pollPageParams: PollPageParams, filters: PollFilters): Page<PollModel>
    fun findAllByLoggedUser(userOwnerId: UUID): List<PollModel>
    fun findAllByStatus(status: PollStatus): List<PollModel>
    fun findById(id: UUID): PollModel
    fun findAllScheduledFromToday(): List<PollModel>
    fun findAllPollsToEndToday(): List<PollModel>
}
