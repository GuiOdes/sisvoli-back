package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.models.UserModel

interface UserService {
    fun save(userRequest: UserRequest): UserResponse
    fun findByUsername(username: String): UserModel
    fun update(userUpdateRequest: UserUpdateRequest, username: String): UserResponse
    fun emailAvailable(email: String): Boolean
}
