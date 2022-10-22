package br.com.sisvoli.services.interfaces

import br.com.sisvoli.models.PasswordRecoveryTokenModel
import java.util.UUID

interface PasswordRecoverTokenService {
    fun deleteById(id: UUID)
    fun generateByUserId(userId: UUID): PasswordRecoveryTokenModel
    fun validateByUserDocument(userDocument: String, userResponse: String): Boolean
    fun deleteByTokenAndUserDocument(token: String, cpf: String)
}
