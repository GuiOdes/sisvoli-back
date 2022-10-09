package br.com.sisvoli.exceptions

abstract class SisvoliBaseRuntimeException(
    override val message: String
) : RuntimeException(message)
