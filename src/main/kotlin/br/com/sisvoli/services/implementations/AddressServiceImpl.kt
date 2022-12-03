package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.api.responses.AddressResponse
import br.com.sisvoli.database.repositories.interfaces.AddressRepository
import br.com.sisvoli.models.AddressModel
import br.com.sisvoli.services.interfaces.AddressService
import br.com.sisvoli.services.interfaces.CityService
import br.com.sisvoli.services.interfaces.StateService
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository,
    private val userService: UserService,
    private val cityService: CityService,
    private val stateService: StateService
) : AddressService {
    override fun save(addressRequest: AddressRequest, userDocument: String): AddressModel {
        val userModel = userService.findByCpf(userDocument)
        return addressRepository.save(addressRequest.toAddressModel(userModel.id!!))
    }

    override fun findByUserDocument(userDocumentRequest: String): AddressResponse {
        val addressModel = addressRepository.findByUserDocument(userDocumentRequest)

        val cityModel = cityService.findById(cityId = addressModel.cityId)

        val stateModel = stateService.findById(cityModel.stateId)

        return addressModel.toAddressResponse(cityModel.name, stateModel.name)
    }
}
