package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidPollNotScheduledUpdateException(
    override val message: String = "Não é possivel alterar uma enquete que não esteja agendada"
) : SisvoliBaseRuntimeException(message)
