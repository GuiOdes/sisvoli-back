package br.com.sisvoli.util

import br.com.sisvoli.exceptions.invalid.InvalidUUIDException
import java.util.UUID

fun stringToUUID(value: String): UUID {
    return runCatching {
        UUID.fromString(value)
    }.onFailure {
        throw InvalidUUIDException()
    }.getOrThrow()
}
