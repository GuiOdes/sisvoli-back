package br.com.sisvoli.services.implementations

import br.com.colman.simplecpfvalidator.isCpf
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.exceptions.invalid.InvalidCPFException
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService, UserService {

    override fun save(userModel: UserRequest): UserResponse {
        return if (isCpf(userModel.cpf)) {
            userRepository.save(userModel).toUserResponse()
        } else {
            throw InvalidCPFException()
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return User(user.username, user.password, listOf(SimpleGrantedAuthority(user.roleName)))
    }

    private fun isCpf(cpf: String) = cpf.isCpf()
}
