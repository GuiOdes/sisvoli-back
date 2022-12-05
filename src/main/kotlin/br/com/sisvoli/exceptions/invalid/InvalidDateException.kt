package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidDateException(
    override val message: String = "Data inválida"
) : SisvoliBaseRuntimeException(message)
