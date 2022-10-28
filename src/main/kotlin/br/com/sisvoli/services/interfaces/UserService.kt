package br.com.sisvoli.services.interfaces

import br.com.sisvoli.api.requests.PasswordRecoverRequest
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.models.UserModel
import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface UserService {
    fun save(userRequest: UserRequest): UserResponse
    fun findByUsername(username: String): UserModel
    fun updateByUsername(userUpdateRequest: UserUpdateRequest, username: String): UserResponse
    fun requestPasswordRecoverByCpf(userCpf: String): UUID
    fun tokenRecoverValidation(passwordRecoverRequest: PasswordRecoverRequest): Boolean
    fun updatePassword(passwordRecoverRequest: PasswordRecoverRequest)
    fun existsByEmail(email: String): Boolean
    fun findByCpf(cpf: String): UserModel
    fun doRefreshToken(authorizationHeader: String?, request: HttpServletRequest, response: HttpServletResponse)
    fun existsByCpf(cpf: String): Boolean
    fun existsByUsername(username: String): Boolean
}
