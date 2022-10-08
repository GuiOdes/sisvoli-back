package br.com.sisvoli.database.repositories

import br.com.sisvoli.models.UserModel

interface UserRepository {
    fun save(userModel: UserModel): UserModel
    fun findByUsername(username: String): UserModel
}
