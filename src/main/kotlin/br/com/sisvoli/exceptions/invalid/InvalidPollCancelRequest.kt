package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidPollCancelRequest(
    override val message: String = "Somente enquetes agendadas poderão ser canceladas"
) : SisvoliBaseRuntimeException(message)
