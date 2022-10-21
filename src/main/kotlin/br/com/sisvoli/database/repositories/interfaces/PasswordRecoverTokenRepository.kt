package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.PasswordRecoveryTokenModel
import java.util.UUID

interface PasswordRecoverTokenRepository {
    fun deleteById(id: UUID)
    fun save(passwordRecoveryTokenModel: PasswordRecoveryTokenModel): PasswordRecoveryTokenModel
}
