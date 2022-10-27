package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.PollModel
import java.util.UUID

interface PollRepository {
    fun save(pollModel: PollModel): PollModel
    fun findAll(): List<PollModel>
    fun findAllByLoggedUser(userID: UUID): List<PollModel>
}
