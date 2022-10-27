package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.models.AddressModel
import br.com.sisvoli.services.interfaces.AddressService
import br.com.sisvoli.util.JWTUtil
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/address")
class AddressController(
    val addressService: AddressService,
    val jwtUtil: JWTUtil
) {
    @PostMapping
    fun save(@RequestBody addressModel: AddressRequest): AddressModel {
        val usernameRequest = jwtUtil.getUsername()
        return addressService.save(addressModel, usernameRequest)
    }
}
