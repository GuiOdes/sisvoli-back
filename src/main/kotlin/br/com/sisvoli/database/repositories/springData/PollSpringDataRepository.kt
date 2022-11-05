package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.PollEntity
import br.com.sisvoli.enums.PollStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface PollSpringDataRepository : JpaRepository<PollEntity, UUID> {
    fun findAllByUserOwnerId(userOwnerId: UUID): List<PollEntity>
    fun findAllByStatus(status: PollStatus): List<PollEntity>
    @Query(
        value = "SELECT * FROM tb_poll tp WHERE DATE(tp.start_date) = CURRENT_DATE AND tp.status = 'SCHEDULED';",
        nativeQuery = true
    )
    fun findAllScheduledPollsFromToday(): List<PollEntity>
    @Query(
        value = "SELECT * FROM tb_poll tp WHERE DATE(tp.end_date) = CURRENT_DATE AND tp.status = 'PROGRESS';",
        nativeQuery = true
    )
    fun findAllPollsToEndToday(): List<PollEntity>
}
