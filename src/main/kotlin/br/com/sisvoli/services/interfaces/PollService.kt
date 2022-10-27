package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.models.PollModel

interface PollService {
    fun save(pollRequest: PollRequest, username: String): PollModel
    fun findAll(): List<PollModel>
    fun findAllByLoggedUser(username: String): List<PollModel>
}
