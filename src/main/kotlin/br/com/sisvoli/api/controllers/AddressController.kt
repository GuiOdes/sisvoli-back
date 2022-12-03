package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.api.responses.AddressResponse
import br.com.sisvoli.models.AddressModel
import br.com.sisvoli.services.interfaces.AddressService
import br.com.sisvoli.util.JWTUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/address")
class AddressController(
    val addressService: AddressService,
    val jwtUtil: JWTUtil
) {
    @PostMapping
    fun save(@RequestBody @Valid addressModel: AddressRequest): AddressModel {
        val userDocumentRequest = jwtUtil.getUserDocument()
        return addressService.save(addressModel, userDocumentRequest)
    }

    @GetMapping
    fun findByLoggedUser(): AddressResponse {
        val userDocumentRequest = jwtUtil.getUserDocument()
        return addressService.findByUserDocument(userDocumentRequest)
    }
}
