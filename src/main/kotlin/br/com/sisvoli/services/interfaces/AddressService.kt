package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.api.responses.AddressResponse
import br.com.sisvoli.models.AddressModel

interface AddressService {
    fun save(addressRequest: AddressRequest, userDocument: String): AddressModel
    fun findByUserDocument(userDocumentRequest: String): AddressResponse
}
