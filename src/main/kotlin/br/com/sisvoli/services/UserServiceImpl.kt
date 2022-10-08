package br.com.sisvoli.services

import br.com.colman.simplecpfvalidator.isCpf
import br.com.sisvoli.database.repositories.UserRepository
import br.com.sisvoli.models.UserModel
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService, UserService {

    override fun save(userModel: UserModel): UserModel {
        return if (isCpf(userModel.cpf)) {
            userRepository.save(userModel)
        } else {
            throw Exception("CPF invalido")
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return User(user.username, user.password, listOf(SimpleGrantedAuthority(user.roleName)))
    }

    private fun isCpf(cpf: String) = cpf.isCpf()
}
