package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.OptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface OptionSpringDataRepository : JpaRepository<OptionEntity, UUID> {
    fun deleteAllByPollEntityId(pollId: UUID)

    fun existsByNameAndPollEntityId(name: String, id: UUID): Boolean
    fun findAllByPollEntityId(pollId: UUID): List<OptionEntity>
}
