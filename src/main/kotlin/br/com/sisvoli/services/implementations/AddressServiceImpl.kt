package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.api.requests.AddressUpdateRequest
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

    override fun updateByUserDocument(
        userDocumentRequest: String,
        addressRequest: AddressUpdateRequest
    ): AddressResponse {
        val addressModel = addressRepository.findByUserDocument(userDocumentRequest)
        val cityModel = cityService.findById(addressRequest.cityId ?: addressModel.cityId)

        return addressRepository.save(
            addressModel.copy(
                zipCode = addressRequest.zipCode ?: addressModel.zipCode,
                number = addressRequest.number ?: addressModel.number,
                street = addressRequest.street ?: addressModel.zipCode,
                district = addressRequest.district ?: addressModel.district,
                complement = addressRequest.complement ?: addressModel.complement,
                cityId = cityModel.id!!
            )
        ).toAddressResponse(cityModel.name, stateService.findById(cityModel.stateId).name)
    }
}
