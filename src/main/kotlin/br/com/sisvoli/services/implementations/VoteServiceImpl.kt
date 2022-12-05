package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.MailRequest
import br.com.sisvoli.database.repositories.interfaces.VoteRepository
import br.com.sisvoli.services.interfaces.EmailService
import br.com.sisvoli.services.interfaces.OptionService
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.services.interfaces.VoteService
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
class VoteServiceImpl(
    val voteRepository: VoteRepository,
    val userService: UserService,
    val emailService: EmailService,
    val pollService: PollService,
    val optionService: OptionService
) : VoteService {
    @Transactional
    override fun addVote(userDocument: String, optionId: UUID) {
        val userModel = userService.findByCpf(userDocument)
        val optionModel = optionService.findById(optionId)
        val pollModel = pollService.findById(optionModel.pollId)

        voteRepository.addVote(userModel.id!!, optionId)

        emailService.sendMail(
            MailRequest(
                emailTo = userModel.email,
                subject = "Confirmação de voto",
                text = "Você votou com sucesso na enquete ${pollModel.title}"
            )
        )
    }
}
