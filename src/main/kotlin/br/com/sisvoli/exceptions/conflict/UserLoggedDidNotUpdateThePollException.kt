package br.com.sisvoli.exceptions.conflict

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class UserLoggedDidNotUpdateThePollException(
    override val message: String = "Somente quem criou a enquete pode edita-la"
) : SisvoliBaseRuntimeException(message)
