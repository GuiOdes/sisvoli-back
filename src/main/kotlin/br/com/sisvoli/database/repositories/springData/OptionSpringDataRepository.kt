package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.OptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OptionSpringDataRepository : JpaRepository<OptionEntity, UUID> {
    fun existsByNameAndPollEntityId(name: String, id: UUID): Boolean
}
