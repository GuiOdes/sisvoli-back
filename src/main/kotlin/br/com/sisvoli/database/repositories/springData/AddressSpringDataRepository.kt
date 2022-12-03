package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface AddressSpringDataRepository : JpaRepository<AddressEntity, UUID> {
    fun findByUserEntityCpf(userDocumentRequest: String): Optional<AddressEntity>
}
