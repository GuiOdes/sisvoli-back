package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel

interface CityRepository {
    fun findById(cityId: Long): CityModel
    fun findStateById(cityId: Long): StateModel
}
