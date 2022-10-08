package br.com.sisvoli.services

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.responses.UserResponse

interface UserService {
    fun save(userModel: UserRequest): UserResponse
}
