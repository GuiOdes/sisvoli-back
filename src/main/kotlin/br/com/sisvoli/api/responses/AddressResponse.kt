package br.com.sisvoli.api.responses

import java.util.UUID

data class AddressResponse(
    val id: UUID? = null,
    val zipCode: String,
    val number: String,
    val street: String,
    val district: String,
    val complement: String,
    val cityId: Long
)
