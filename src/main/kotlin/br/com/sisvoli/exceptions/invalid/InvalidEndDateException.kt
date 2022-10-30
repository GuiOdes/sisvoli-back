package br.com.sisvoli.exceptions.invalid

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class InvalidEndDateException(
    override val message: String = "A Data de finalização da enquete precisa ser maior que a data de inicialização da enquete"
) : SisvoliBaseRuntimeException(message)
