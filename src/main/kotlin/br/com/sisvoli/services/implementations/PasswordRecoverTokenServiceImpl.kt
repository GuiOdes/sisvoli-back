package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.PasswordRecoverTokenRepository
import br.com.sisvoli.models.PasswordRecoveryTokenModel
import br.com.sisvoli.services.interfaces.PasswordRecoverTokenService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PasswordRecoverTokenServiceImpl(
    private val passwordRecoverTokenRepository: PasswordRecoverTokenRepository
) : PasswordRecoverTokenService {
    override fun deleteById(id: UUID) {
        passwordRecoverTokenRepository.deleteById(id)
    }

    override fun generateByUserId(userId: UUID) {
        val token =
            "${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}"

        passwordRecoverTokenRepository.save(
            PasswordRecoveryTokenModel(
                userId = userId,
                token = token
            )
        )
    }

    private fun randomLetter() = ('A'..'Z').random()
}
