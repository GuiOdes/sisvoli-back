package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.CityModel

interface CityRepository {
    fun findById(cityId: Long): CityModel
}
