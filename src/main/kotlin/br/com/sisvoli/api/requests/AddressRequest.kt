package br.com.sisvoli.api.requests

import br.com.sisvoli.models.AddressModel
import java.util.UUID
import javax.validation.constraints.NotBlank

data class AddressRequest(
    @field: NotBlank(message = "Digite seu zipCode")
    val zipCode: String,
    @field: NotBlank(message = "Digite o número da sua residencia")
    val number: String,
    @field: NotBlank(message = "Digite a rua da sua residencia")
    val street: String,
    @field: NotBlank(message = "Informe seu bairro")
    val district: String,
    @field: NotBlank(message = "Digite um complemento")
    val complement: String,
    @field: NotBlank(message = "Informe sua cidade")
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
