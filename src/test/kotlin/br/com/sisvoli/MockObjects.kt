package br.com.sisvoli

import br.com.sisvoli.database.entities.StateEntity
import br.com.sisvoli.models.StateModel

fun stateModelMock(
    id: Long = 1,
    name: String = "Goiás",
    uf: String = "GO"
) = StateModel(id, name, uf)

fun stateEntityMock(
    id: Long = 1,
    name: String = "Goiás",
    uf: String = "GO"
) = StateEntity(id, name, uf)
