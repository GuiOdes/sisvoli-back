package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class UserNotFoundException(
    override val message: String = "User not found"
) : SisvoliBaseRuntimeException(message)
