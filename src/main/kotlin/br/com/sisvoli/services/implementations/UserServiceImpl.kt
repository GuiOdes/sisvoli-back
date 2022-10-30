package br.com.sisvoli.services.implementations

import br.com.colman.simplecpfvalidator.isCpf
import br.com.sisvoli.api.requests.MailRequest
import br.com.sisvoli.api.requests.PasswordRecoverRequest
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.conflict.PasswordRecoverAlreadyExistsException
import br.com.sisvoli.exceptions.invalid.InvalidCPFException
import br.com.sisvoli.exceptions.invalid.InvalidTokenException
import br.com.sisvoli.models.UserModel
import br.com.sisvoli.services.interfaces.EmailService
import br.com.sisvoli.services.interfaces.PasswordRecoverTokenService
import br.com.sisvoli.services.interfaces.UserService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import javax.transaction.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val passwordRecoverTokenService: PasswordRecoverTokenService,
    private val emailService: EmailService
) : UserDetailsService, UserService {

    override fun save(userRequest: UserRequest): UserResponse {
        return if (isCpf(userRequest.cpf)) {
            val userModel = userRequest.toUserModel(RoleEnum.DEFAULT.name).copy(
                password = encodePassword(userRequest.password),
                cpf = userRequest.cpf
                    .replace("-", "")
                    .replace(".", "")
            )
            userRepository.save(userModel).toUserResponse()
        } else {
            throw InvalidCPFException()
        }
    }

    override fun findByUsername(username: String): UserModel {
        return userRepository.findByUsername(username)
    }

    override fun updateByUsername(userUpdateRequest: UserUpdateRequest, username: String): UserResponse {
        val userModel = findByUsername(username)
        val userToSave = userModel.copy(
            name = userUpdateRequest.name ?: userModel.name,
            gender = userUpdateRequest.gender ?: userModel.gender,
            email = userUpdateRequest.email ?: userModel.email,
            password = userUpdateRequest.password?.let { encodePassword(it) } ?: userModel.password,
            phoneNumber = userUpdateRequest.phoneNumber ?: userModel.phoneNumber,
            birthDate = userUpdateRequest.birthDate ?: userModel.birthDate,
        )
        return userRepository.save(userToSave).toUserResponse()
    }

    @Transactional
    override fun requestPasswordRecoverByCpf(userCpf: String): UUID {
        val userModel = userRepository.findByCpf(userCpf)
        val recoverTokenModel = userRepository.findRecoverTokenByUserId(userModel.id!!)

        if (recoverTokenModel != null) {
            val dayAfterRecoverCreation = recoverTokenModel.creationDate!!.plusDays(1)
            val hourDifference = ChronoUnit.HOURS.between(LocalDateTime.now(), dayAfterRecoverCreation)

            if (hourDifference > 0) {
                throw PasswordRecoverAlreadyExistsException(hourDifference)
            }

            passwordRecoverTokenService.deleteById(recoverTokenModel.id!!)
        }

        val token = passwordRecoverTokenService.generateByUserId(userModel.id).token

        emailService.sendMail(
            MailRequest(
                emailTo = userModel.email,
                subject = "Recuperação de senha",
                text = "Olá, ${userModel.username}! O seu token para recuperação de senha é $token."
            )
        )
        return userModel.id
    }

    override fun tokenRecoverValidation(passwordRecoverRequest: PasswordRecoverRequest): Boolean {
        return passwordRecoverTokenService.validateByUserDocument(
            passwordRecoverRequest.cpf,
            passwordRecoverRequest.token
        )
    }

    @Transactional
    override fun updatePassword(passwordRecoverRequest: PasswordRecoverRequest) {
        val isValidToken = passwordRecoverTokenService.validateByUserDocument(
            passwordRecoverRequest.cpf,
            passwordRecoverRequest.token
        )

        if (isValidToken.not()) {
            throw InvalidTokenException()
        }

        updateByUsername(
            UserUpdateRequest(password = passwordRecoverRequest.newPassword),
            userRepository.findByCpf(passwordRecoverRequest.cpf).username
        )

        passwordRecoverTokenService.deleteByTokenAndUserDocument(
            passwordRecoverRequest.token,
            passwordRecoverRequest.cpf
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return User(user.username, user.password, listOf(SimpleGrantedAuthority(user.roleName)))
    }

    override fun emailAvailable(email: String): Boolean {
        return !userRepository.existsByEmail(email)
    }

    override fun cpfAvailable(cpf: String): Boolean {
        return !userRepository.existsByCpf(cpf)
    }

    override fun usernameAvailable(username: String): Boolean {
        return !userRepository.existsByUsername(username)
    }
    private fun isCpf(cpf: String) = cpf.isCpf()

    private fun encodePassword(password: String) = passwordEncoder.encode(password)
}
