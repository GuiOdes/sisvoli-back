package br.com.sisvoli.services.interfaces

import java.util.UUID

interface PasswordRecoverTokenService {
    fun deleteById(id: UUID)
    fun generateByUserId(userId: UUID)
}
