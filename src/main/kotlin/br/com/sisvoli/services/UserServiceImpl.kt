package br.com.sisvoli.services

import br.com.sisvoli.database.entities.UserEntity
import br.com.sisvoli.database.repositories.RoleSpringDataRepository
import br.com.sisvoli.database.repositories.UserSpringDataRepository
import br.com.sisvoli.models.UserModel
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userSpringDataRepository: UserSpringDataRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleSpringDataRepository: RoleSpringDataRepository
) : UserDetailsService {

    fun save(userModel: UserModel): UserModel {
        val userEntity = UserEntity.of(userModel, roleSpringDataRepository.findByName(userModel.roleName))
        userEntity.password = passwordEncoder.encode(userModel.password)

        return userSpringDataRepository.save(userEntity).toUserModel()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userSpringDataRepository.findByUsername(username)
        return User(user.username, user.password, listOf(SimpleGrantedAuthority(user.role.name)))
    }
}
