package br.com.sisvoli.database.repositories.interfaces

import java.util.UUID

interface VoteRepository {
    fun addVote(userId: UUID, optionId: UUID)
}
