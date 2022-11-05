package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.UserOptionKey
import br.com.sisvoli.database.entities.VoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface VoteSpringDataRepository : JpaRepository<VoteEntity, UserOptionKey> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(
        value = "INSERT INTO tb_vote VALUES (:userId, :optionId, now())",
        nativeQuery = true
    )
    fun save(userId: UUID, optionId: UUID)
    fun existsByOptionEntityPollEntityIdAndUserEntityId(pollId: UUID, userId: UUID): Boolean
}
