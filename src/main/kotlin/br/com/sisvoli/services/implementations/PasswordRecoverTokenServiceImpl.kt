package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.PasswordRecoverTokenRepository
import br.com.sisvoli.exceptions.notFound.RecoverTokenNotFoundException
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

    override fun generateByUserId(userId: UUID): PasswordRecoveryTokenModel {
        val token =
            "${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}${randomLetter()}"

        return passwordRecoverTokenRepository.save(
            PasswordRecoveryTokenModel(
                userId = userId,
                token = token
            )
        )
    }

    override fun validateByUserDocument(userDocument: String, userResponse: String): Boolean {
        val findRecover = passwordRecoverTokenRepository.findByUserDocument(userDocument)
            ?: throw RecoverTokenNotFoundException()

        return findRecover.token == userResponse
    }

    override fun deleteByTokenAndUserDocument(token: String, cpf: String) {
        passwordRecoverTokenRepository.deleteByTokenAndUserDocument(token, cpf)
    }

    private fun randomLetter() = ('A'..'Z').random()
}
