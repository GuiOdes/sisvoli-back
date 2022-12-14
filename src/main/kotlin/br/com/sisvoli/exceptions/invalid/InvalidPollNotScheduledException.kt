package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidPollNotScheduledException(
    override val message: String = "Não é possivel alterar opções em enquetes não agendadas"
) : SisvoliBaseRuntimeException(message)
