package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.StateRepository
import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel
import br.com.sisvoli.services.interfaces.StateService
import org.springframework.stereotype.Service

@Service
class StateServiceImpl(
    val stateRepository: StateRepository
) : StateService {
    override fun findAll(): List<StateModel> {
        return stateRepository.findAll()
    }

    override fun findCitiesById(stateId: Long): List<CityModel> {
        return stateRepository.findCitiesById(stateId)
    }
}
