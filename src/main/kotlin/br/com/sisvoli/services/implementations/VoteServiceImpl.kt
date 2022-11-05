package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.VoteRepository
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.services.interfaces.VoteService
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
class VoteServiceImpl(
    val voteRepository: VoteRepository,
    val userService: UserService
) : VoteService {
    @Transactional
    override fun addVote(userDocument: String, optionId: UUID) {
        voteRepository.addVote(userService.findByCpf(userDocument).id!!, optionId)
    }
}
