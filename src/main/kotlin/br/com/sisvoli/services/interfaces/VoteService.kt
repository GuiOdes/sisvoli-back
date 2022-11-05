package br.com.sisvoli.services.interfaces

import java.util.UUID

interface VoteService {
    fun addVote(userDocument: String, optionId: UUID)
}
