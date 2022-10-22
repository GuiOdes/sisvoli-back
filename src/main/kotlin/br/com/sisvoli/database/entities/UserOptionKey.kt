package br.com.sisvoli.database.entities

import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserOptionKey(
    @Column(name = "user_id")
    val userId: UUID? = null,
    @Column(name = "option_id")
    val optionId: UUID? = null
) : Serializable
