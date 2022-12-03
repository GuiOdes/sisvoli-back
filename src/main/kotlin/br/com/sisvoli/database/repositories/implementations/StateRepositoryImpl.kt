package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.repositories.interfaces.StateRepository
import br.com.sisvoli.database.repositories.springData.StateSpringDataRepository
import br.com.sisvoli.exceptions.notFound.StateNotFoundException
import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel
import org.springframework.stereotype.Component

@Component
class StateRepositoryImpl(
    private val stateSpringDataRepository: StateSpringDataRepository
) : StateRepository {
    override fun findAll(): List<StateModel> {
        return stateSpringDataRepository.findAll()
            .map { it.toStateModel() }
    }

    override fun findCitiesById(stateId: Long): List<CityModel> {
        return stateSpringDataRepository.findById(stateId).orElseThrow { StateNotFoundException() }
            .cityList?.map { it.toCityModel() }.orEmpty()
    }

    override fun findById(stateId: Long): StateModel {
        return stateSpringDataRepository.findById(stateId)
            .orElseThrow { StateNotFoundException() }
            .toStateModel()
    }
}
