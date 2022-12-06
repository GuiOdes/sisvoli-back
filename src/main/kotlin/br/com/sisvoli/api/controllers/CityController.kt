package br.com.sisvoli.api.controllers

import br.com.sisvoli.models.CityModel
import br.com.sisvoli.models.StateModel
import br.com.sisvoli.services.interfaces.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/city")
class CityController(
    val cityService: CityService
) {
    @GetMapping("/{cityId}")
    fun findById(@PathVariable cityId: Long): CityModel {
        return cityService.findById(cityId)
    }
    @GetMapping("/{cityId}/state")
    fun findByStateId(@PathVariable cityId: Long): StateModel {
        return cityService.findStateById(cityId)
    }
}
