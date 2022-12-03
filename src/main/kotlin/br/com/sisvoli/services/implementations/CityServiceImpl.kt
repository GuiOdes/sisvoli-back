package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.CityRepository
import br.com.sisvoli.models.CityModel
import br.com.sisvoli.services.interfaces.CityService
import org.springframework.stereotype.Service

@Service
class CityServiceImpl(
    val cityRepository: CityRepository
) : CityService {
    override fun findById(cityId: Long): CityModel {
        return cityRepository.findById(cityId)
    }
}
