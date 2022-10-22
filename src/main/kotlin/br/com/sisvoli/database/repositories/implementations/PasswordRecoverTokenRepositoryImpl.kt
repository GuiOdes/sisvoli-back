package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.PasswordRecoveryTokenEntity
import br.com.sisvoli.database.repositories.interfaces.PasswordRecoverTokenRepository
import br.com.sisvoli.database.repositories.springData.PasswordRecoveryTokenSpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.PasswordRecoveryTokenModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PasswordRecoverTokenRepositoryImpl(
    private val passwordRecoveryTokenSpringDataRepository: PasswordRecoveryTokenSpringDataRepository,
    private val userSpringDataRepository: UserSpringDataRepository
) : PasswordRecoverTokenRepository {
    override fun deleteById(id: UUID) {
        passwordRecoveryTokenSpringDataRepository.deleteById(id)
    }

    override fun save(passwordRecoveryTokenModel: PasswordRecoveryTokenModel): PasswordRecoveryTokenModel {
        val userEntity = userSpringDataRepository
            .findById(passwordRecoveryTokenModel.userId).orElseThrow { UserNotFoundException() }

        return passwordRecoveryTokenSpringDataRepository.save(
            PasswordRecoveryTokenEntity.of(
                passwordRecoveryTokenModel,
                userEntity
            )
        ).toPasswordRecoveryTokenModel()
    }

    override fun findByUserDocument(userDocument: String): PasswordRecoveryTokenModel? {
        return passwordRecoveryTokenSpringDataRepository
            .findByUserEntityCpf(userDocument)?.toPasswordRecoveryTokenModel()
    }

    override fun deleteByTokenAndUserDocument(token: String, cpf: String) {
        passwordRecoveryTokenSpringDataRepository.deleteByTokenAndUserEntityCpf(token, cpf)
    }
}
