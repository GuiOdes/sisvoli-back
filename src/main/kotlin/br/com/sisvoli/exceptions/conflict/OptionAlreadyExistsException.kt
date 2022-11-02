package br.com.sisvoli.exceptions.conflict

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class OptionAlreadyExistsException(
    override val message: String = "Essa opção já está criada nessa enquete"
) : SisvoliBaseRuntimeException(message)
