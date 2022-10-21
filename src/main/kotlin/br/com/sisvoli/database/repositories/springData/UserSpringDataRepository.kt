package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserSpringDataRepository : JpaRepository<UserEntity, UUID> {

    fun findByUsername(username: String): UserEntity?
    fun existsByEmail(email: String): Boolean
}
