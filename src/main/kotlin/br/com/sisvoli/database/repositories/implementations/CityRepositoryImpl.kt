package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.repositories.interfaces.CityRepository
import br.com.sisvoli.database.repositories.springData.CitySpringDataRepository
import br.com.sisvoli.exceptions.notFound.CityNotFoundException
import br.com.sisvoli.models.CityModel
import org.springframework.stereotype.Component

@Component
class CityRepositoryImpl(
    private val citySpringDataRepository: CitySpringDataRepository
) : CityRepository {
    override fun findById(cityId: Long): CityModel {
        return citySpringDataRepository.findById(cityId).orElseThrow { CityNotFoundException() }
            .toCityModel()
    }
}
