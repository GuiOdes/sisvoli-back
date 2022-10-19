package br.com.sisvoli.models

import java.util.UUID

class OptionModel(
    val id: UUID? = null,
    val name: String,
    val pollId: UUID
)
