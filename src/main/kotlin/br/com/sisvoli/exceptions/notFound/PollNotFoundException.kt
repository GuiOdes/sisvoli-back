package br.com.sisvoli.exceptions.notFound

import br.com.sisvoli.exceptions.SisvoliBaseRuntimeException

class PollNotFoundException(
    override val message: String = "Poll not found"
) : SisvoliBaseRuntimeException(message)
