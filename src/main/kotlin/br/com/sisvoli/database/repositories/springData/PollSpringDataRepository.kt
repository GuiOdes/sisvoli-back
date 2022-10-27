package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.PollEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PollSpringDataRepository: JpaRepository<PollEntity, UUID> {

}