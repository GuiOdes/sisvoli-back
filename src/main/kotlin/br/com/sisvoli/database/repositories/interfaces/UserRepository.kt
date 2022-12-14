package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.PasswordRecoveryTokenModel
import br.com.sisvoli.models.UserModel
import java.util.UUID

interface UserRepository {
    fun save(userModel: UserModel): UserModel
    fun findById(id: UUID): UserModel
    fun findByUsername(username: String): UserModel
    fun existsByEmail(email: String): Boolean
    fun findByCpf(cpf: String): UserModel
    fun findRecoverTokenByUserId(userId: UUID): PasswordRecoveryTokenModel?
    fun existsByCpf(cpf: String): Boolean
    fun existsByUsername(username: String): Boolean
}
