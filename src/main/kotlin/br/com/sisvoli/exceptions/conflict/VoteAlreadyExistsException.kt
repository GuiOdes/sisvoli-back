package br.com.sisvoli.exceptions.conflict

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class VoteAlreadyExistsException(
    override val message: String = "Essa enquete já foi votada por esse usuário"
) : SisvoliBaseRuntimeException(message)
