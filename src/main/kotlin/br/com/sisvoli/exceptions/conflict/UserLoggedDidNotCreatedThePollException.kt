package br.com.sisvoli.exceptions.conflict

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class UserLoggedDidNotCreatedThePollException(
    override val message: String = "Somente quem criou a enquete pode criar as opções de votação"
) : SisvoliBaseRuntimeException(message)
