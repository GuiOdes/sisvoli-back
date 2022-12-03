package br.com.sisvoli.services.interfaces

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel

interface StateService {
    fun findAll(): List<StateModel>
    fun findCitiesById(stateId: Long): List<CityModel>
    fun findById(stateId: Long): StateModel
}
