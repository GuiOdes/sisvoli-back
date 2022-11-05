package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.OptionRequest
import br.com.sisvoli.models.OptionModel
import br.com.sisvoli.services.interfaces.OptionService
import br.com.sisvoli.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/option")
class OptionController(
    val jwtUtil: JWTUtil,
    val optionService: OptionService
) {
    @PostMapping("/new")
    fun save(@RequestBody @Valid optionRequest: OptionRequest): ResponseEntity<OptionModel> {
        val userDocument = jwtUtil.getUserDocument()
        return ResponseEntity(optionService.save(optionRequest, userDocument), HttpStatus.CREATED)
    }
}
