package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.UserEntity
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.database.repositories.springData.PasswordRecoveryTokenSpringDataRepository
import br.com.sisvoli.database.repositories.springData.RoleSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.PasswordRecoveryTokenModel
import br.com.sisvoli.models.UserModel
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepositoryImpl(
    private val userSpringDataRepository: UserSpringDataRepository,
    private val roleSpringDataRepository: RoleSpringDataRepository,
    private val passwordRecoveryTokenSpringDataRepository: PasswordRecoveryTokenSpringDataRepository
) : UserRepository {
    override fun save(userModel: UserModel): UserModel {
        logger.info { "Starting to save user with document ${userModel.cpf}..." }
        val userEntity = UserEntity.of(userModel, roleSpringDataRepository.findByName(RoleEnum.DEFAULT.name))
        return userSpringDataRepository.save(userEntity).toUserModel()
    }

    override fun findById(id: UUID): UserModel {
        logger.info { "Starting to find user by id: $id..." }
        return userSpringDataRepository.findById(id).orElseThrow { UserNotFoundException() }.toUserModel()
    }

    override fun findByUsername(username: String): UserModel {
        logger.info { "Starting to find user by username: $username..." }
        return userSpringDataRepository.findByUsername(username)?.toUserModel() ?: throw UserNotFoundException()
    }

    override fun existsByEmail(email: String): Boolean {
        logger.info { "Starting to check if user with email $email exists..." }
        return userSpringDataRepository.existsByEmail(email)
    }

    override fun findByCpf(cpf: String): UserModel {
        logger.info { "Starting to find user by cpf: $cpf..." }
        return userSpringDataRepository.findByCpf(cpf)?.toUserModel() ?: throw UserNotFoundException()
    }

    override fun findRecoverTokenByUserId(userId: UUID): PasswordRecoveryTokenModel? {
        logger.info { "Starting to find recovery token by userId: $userId..." }
        return passwordRecoveryTokenSpringDataRepository.findByUserEntityId(userId)?.toPasswordRecoveryTokenModel()
    }

    override fun existsByCpf(cpf: String): Boolean {
        logger.info { "Starting to check if user with document $cpf exists..." }
        return userSpringDataRepository.existsByCpf(cpf)
    }

    override fun existsByUsername(username: String): Boolean {
        logger.info { "Starting to check if user with username $username exists..." }
        return userSpringDataRepository.existsByUsername(username)
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
