package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel

interface StateRepository {
    fun findAll(): List<StateModel>
    fun findCitiesById(stateId: Long): List<CityModel>
    fun findById(stateId: Long): StateModel
}
