package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.UserEntity
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.database.repositories.springData.RoleSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.UserModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepositoryImpl(
    private val userSpringDataRepository: UserSpringDataRepository,
    private val roleSpringDataRepository: RoleSpringDataRepository
) : UserRepository {
    override fun save(userModel: UserModel): UserModel {
        val userEntity = UserEntity.of(userModel, roleSpringDataRepository.findByName(RoleEnum.DEFAULT.name))
        return userSpringDataRepository.save(userEntity).toUserModel()
    }

    override fun findById(id: UUID): UserModel {
        return userSpringDataRepository.findById(id).orElseThrow { UserNotFoundException() }.toUserModel()
    }

    override fun findByUsername(username: String): UserModel {
        return userSpringDataRepository.findByUsername(username)?.toUserModel() ?: throw UserNotFoundException()
    }
}
