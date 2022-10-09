package br.com.sisvoli.services.implementations

import br.com.sisvoli.database.repositories.interfaces.AddressRepository
import br.com.sisvoli.models.AddressModel
import br.com.sisvoli.services.interfaces.AddressService

class AddressServiceImpl(
    private val addressRepository: AddressRepository
) : AddressService {
    override fun save(addressModel: AddressModel): AddressModel {
        return addressRepository.save(addressModel)
    }
}
