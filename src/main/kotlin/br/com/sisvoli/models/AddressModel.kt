package br.com.sisvoli.models

import br.com.sisvoli.api.responses.AddressResponse
import java.util.UUID

data class AddressModel(
    val id: UUID? = null,
    val zipCode: String,
    val number: String,
    val street: String,
    val district: String,
    val complement: String,
    val cityId: Long,
    val userId: UUID
) {

    fun toAddressResponse(
        cityName: String,
        stateName: String
    ) = AddressResponse(
        id = id,
        zipCode = zipCode,
        number = number,
        street = street,
        district = district,
        complement = complement,
        cityName = cityName,
        stateName = stateName
    )
}
