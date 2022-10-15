package br.com.sisvoli.database.repositories.interfaces

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.models.UserModel

interface UserRepository {
    fun save(userModel: UserRequest): UserModel
    fun findByUsername(username: String): UserModel
}
