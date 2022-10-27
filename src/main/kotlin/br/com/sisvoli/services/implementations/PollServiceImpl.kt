package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.database.repositories.interfaces.PollRepository
import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.models.PollModel
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PollServiceImpl (
    private val pollRepository: PollRepository,
    private val userService: UserService
): PollService{
    override fun save(pollRequest: PollRequest, username: String): PollModel {
        val userId = userService.findByUsername(username).id
        val pollStatus = if (pollRequest.startDate > LocalDateTime.now()){
            PollStatus.SCHEDULED
        }else{
            PollStatus.PROGRESS
        }
        val pollModel = pollRequest.toPollModel(userId!!, pollStatus)
        return pollRepository.save(pollModel)
    }
}