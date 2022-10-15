package br.com.sisvoli.api.requests

import br.com.sisvoli.models.AddressModel
import java.util.UUID

data class AddressRequest(
    val zipCode: String,
    val number: String,
    val street: String,
    val district: String,
    val complement: String,
    val cityId: Long
) {
    fun toAddressModel(userId: UUID) = AddressModel(
        zipCode = zipCode,
        number = number,
        street = street,
        district = district,
        complement = complement,
        cityId = cityId,
        userId = userId
    )
}
