package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class RecoverTokenNotFoundException(
    override val message: String = "Token not found"
) : SisvoliBaseRuntimeException(message)
