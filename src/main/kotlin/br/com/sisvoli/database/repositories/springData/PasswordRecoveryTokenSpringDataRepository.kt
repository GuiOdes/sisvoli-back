package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.PasswordRecoveryTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PasswordRecoveryTokenSpringDataRepository : JpaRepository<PasswordRecoveryTokenEntity, UUID> {
    fun findByUserEntityId(userId: UUID): PasswordRecoveryTokenEntity?
    fun findByUserEntityCpf(cpf: String): PasswordRecoveryTokenEntity?
    fun deleteByTokenAndUserEntityCpf(token: String, cpf: String)
}
