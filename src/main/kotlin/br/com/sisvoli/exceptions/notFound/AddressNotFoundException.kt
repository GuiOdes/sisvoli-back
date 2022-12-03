package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class AddressNotFoundException(
    override val message: String = "Address not found"
) : SisvoliBaseRuntimeException(message)
