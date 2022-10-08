package br.com.sisvoli.services

import br.com.sisvoli.models.UserModel

interface UserService {
    fun save(userModel: UserModel): UserModel
}
