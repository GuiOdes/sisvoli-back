package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.CityEntity
import br.com.sisvoli.models.StateModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CitySpringDataRepository : JpaRepository<CityEntity, Long>
