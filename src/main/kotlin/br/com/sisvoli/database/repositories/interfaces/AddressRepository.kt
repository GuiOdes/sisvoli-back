package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.models.AddressModel

interface AddressRepository {
    fun save(addressModel: AddressModel): AddressModel
    fun findByUserDocument(userDocumentRequest: String): AddressModel
}
