package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.database.entities.UserEntity
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.database.repositories.springData.RoleSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.UserModel
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val userSpringDataRepository: UserSpringDataRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleSpringDataRepository: RoleSpringDataRepository
) : UserRepository {
    override fun save(userModel: UserRequest): UserModel {
        val userEntity = UserEntity.of(userModel, roleSpringDataRepository.findByName(RoleEnum.DEFAULT.name))
        userEntity.password = passwordEncoder.encode(userModel.password)
        return userSpringDataRepository.save(userEntity).toUserModel()
    }

    override fun findByUsername(username: String): UserModel {
        return userSpringDataRepository.findByUsername(username)?.toUserModel() ?: throw UserNotFoundException()
    }
}
