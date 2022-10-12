package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.database.repositories.interfaces.AddressRepository
import br.com.sisvoli.models.AddressModel
import br.com.sisvoli.services.interfaces.AddressService
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository,
    private val userService: UserService
) : AddressService {
    override fun save(addressRequest: AddressRequest, username: String): AddressModel {
        val userModel = userService.findByUsername(username)
        return addressRepository.save(addressRequest.toAddressModel(userModel.id!!))
    }
}
