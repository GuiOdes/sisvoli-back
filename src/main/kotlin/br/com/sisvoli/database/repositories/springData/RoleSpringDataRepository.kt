package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleSpringDataRepository : JpaRepository<RoleEntity, Long> {
    fun findByName(name: String): RoleEntity
}
