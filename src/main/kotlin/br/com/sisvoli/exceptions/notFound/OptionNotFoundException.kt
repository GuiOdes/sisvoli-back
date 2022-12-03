package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class OptionNotFoundException(
    override val message: String = "Option not found"
) : SisvoliBaseRuntimeException(message)
