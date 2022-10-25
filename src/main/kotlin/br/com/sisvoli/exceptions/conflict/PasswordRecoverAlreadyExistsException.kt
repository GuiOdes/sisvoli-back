package br.com.sisvoli.exceptions.conflict

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class PasswordRecoverAlreadyExistsException(
    private val hoursRemaining: Long,
    override val message: String = "Já foi gerado um token de recuperação de senha para essa conta." +
        " Aguarde $hoursRemaining horas para gerar um novo."
) : SisvoliBaseRuntimeException(message)
