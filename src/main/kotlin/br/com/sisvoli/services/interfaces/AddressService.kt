package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.models.AddressModel

interface AddressService {
    fun save(addressRequest: AddressRequest, username: String): AddressModel
}
