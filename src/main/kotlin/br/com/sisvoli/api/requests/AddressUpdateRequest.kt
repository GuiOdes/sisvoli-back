package br.com.sisvoli.api.requests

data class AddressUpdateRequest(
    val zipCode: String? = null,
    val number: String? = null,
    val street: String? = null,
    val district: String? = null,
    val complement: String? = null,
    val cityId: Long? = null
)
