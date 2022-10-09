package br.com.sisvoli.database.repositories.springData

import br.com.sisvoli.database.entities.CityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CitySpringDataRepository : JpaRepository<CityEntity, Long>
