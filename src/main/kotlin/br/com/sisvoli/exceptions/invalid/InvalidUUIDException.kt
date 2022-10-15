package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidUUIDException(
    override val message: String = "UUID inválido"
) : SisvoliBaseRuntimeException(message)
