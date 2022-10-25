package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidTokenException(
    override val message: String = "Token inválido"
) : SisvoliBaseRuntimeException(message)
