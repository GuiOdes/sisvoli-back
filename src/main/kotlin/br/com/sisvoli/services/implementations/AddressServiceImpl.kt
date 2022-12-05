package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.AddressRequest
import br.com.sisvoli.api.requests.AddressUpdateRequest
import br.com.sisvoli.api.responses.AddressResponse
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
    override fun save(addressRequest: AddressRequest, userDocument: String): AddressModel {
        val userModel = userService.findByCpf(userDocument)
        return addressRepository.save(addressRequest.toAddressModel(userModel.id!!))
    }

    override fun findByUserDocument(userDocumentRequest: String): AddressResponse {
        val addressModel = addressRepository.findByUserDocument(userDocumentRequest)

        return addressModel.toAddressResponse()
    }

    override fun updateByUserDocument(
        userDocumentRequest: String,
        addressRequest: AddressUpdateRequest
    ): AddressResponse {
        val addressModel = addressRepository.findByUserDocument(userDocumentRequest)

        return addressRepository.save(
            addressModel.copy(
                zipCode = addressRequest.zipCode ?: addressModel.zipCode,
                number = addressRequest.number ?: addressModel.number,
                street = addressRequest.street ?: addressModel.zipCode,
                district = addressRequest.district ?: addressModel.district,
                complement = addressRequest.complement ?: addressModel.complement,
                cityId = addressRequest.cityId ?: addressModel.cityId
            )
        ).toAddressResponse()
    }
}
