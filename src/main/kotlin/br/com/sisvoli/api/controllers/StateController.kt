package br.com.sisvoli.api.controllers

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel
import br.com.sisvoli.services.interfaces.StateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/state")
class StateController(
    val stateService: StateService
) {

    @GetMapping("/all")
    fun findAll(): List<StateModel> {
        return stateService.findAll()
    }

    @GetMapping("/{stateId}/cities")
    fun findCitiesById(@PathVariable stateId: Long): List<CityModel> {
        return stateService.findCitiesById(stateId)
    }
}
