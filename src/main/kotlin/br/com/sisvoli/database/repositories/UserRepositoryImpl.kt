package br.com.sisvoli.database.repositories

import br.com.sisvoli.database.entities.UserEntity
import br.com.sisvoli.models.UserModel
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val userSpringDataRepository: UserSpringDataRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleSpringDataRepository: RoleSpringDataRepository
) : UserRepository {
    override fun save(userModel: UserModel): UserModel {
        val userEntity = UserEntity.of(userModel, roleSpringDataRepository.findByName(userModel.roleName))
        userEntity.password = passwordEncoder.encode(userModel.password)
        return userSpringDataRepository.save(userEntity).toUserModel()
    }

    override fun findByUsername(username: String): UserModel {
        return userSpringDataRepository.findByUsername(username).toUserModel()
    }
}
