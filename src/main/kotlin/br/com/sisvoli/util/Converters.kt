package br.com.sisvoli.util

import br.com.sisvoli.exceptions.invalid.InvalidUUIDException
import java.util.UUID

const val MINUTES_PER_HOUR = 60
const val MILLIS_PER_MINUTE = 1000

fun stringToUUID(value: String): UUID {
    return runCatching {
        UUID.fromString(value)
    }.onFailure {
        throw InvalidUUIDException()
    }.getOrThrow()
}
fun getMillisByMinute(minutes: Int) = minutes * MINUTES_PER_HOUR * MILLIS_PER_MINUTE
