package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidRefreshTokenException(
    override val message: String = "Refresh token inválido"
) : SisvoliBaseRuntimeException(message)
