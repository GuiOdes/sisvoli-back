package br.com.sisvoli.models

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
)
