package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.PollModel

interface PollRepository {
    fun save(pollModel: PollModel): PollModel
}