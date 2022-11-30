package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class StateNotFoundException(
    override val message: String = "State not found"
) : SisvoliBaseRuntimeException(message)
