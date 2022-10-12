package br.com.sisvoli.exceptions.auth

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidAuthenticationUsername(
    override val message: String = "Nome de usuário inválido na autenticação"
) : SisvoliBaseRuntimeException(message)
