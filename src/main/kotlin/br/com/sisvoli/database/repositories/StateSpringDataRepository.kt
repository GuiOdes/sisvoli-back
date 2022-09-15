package br.com.sisvoli.database.repositories

import br.com.sisvoli.database.entities.StateEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StateSpringDataRepository: JpaRepository<StateEntity, Long>