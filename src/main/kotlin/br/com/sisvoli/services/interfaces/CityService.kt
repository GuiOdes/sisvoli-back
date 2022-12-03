package br.com.sisvoli.services.interfaces

import br.com.sisvoli.models.CityModel

interface CityService {
    fun findById(cityId: Long): CityModel
}
