package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidCPFException(
    override val message: String = "CPF inválido"
) : SisvoliBaseRuntimeException(message)
