package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class CityNotFoundException(
    override val message: String = "City not found"
) : SisvoliBaseRuntimeException(message)
