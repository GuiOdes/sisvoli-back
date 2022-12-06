package br.com.sisvoli.services.interfaces

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel

interface CityService {
    fun findById(cityId: Long): CityModel
    fun findStateById(cityId: Long): StateModel
}
