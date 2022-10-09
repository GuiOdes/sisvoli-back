package br.com.sisvoli.services.interfaces

import br.com.sisvoli.models.AddressModel

interface AddressService {
    fun save(addressModel: AddressModel): AddressModel
}
