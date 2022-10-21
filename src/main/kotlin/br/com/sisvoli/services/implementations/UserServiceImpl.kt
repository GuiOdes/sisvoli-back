package br.com.sisvoli.services.implementations

import br.com.colman.simplecpfvalidator.isCpf
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.conflict.PasswordRecoverAlreadyExistsException
import br.com.sisvoli.exceptions.invalid.InvalidCPFException
import br.com.sisvoli.models.UserModel
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

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val passwordRecoverTokenService: PasswordRecoverTokenService
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
    override fun update(userUpdateRequest: UserUpdateRequest, username: String): UserResponse {
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

    override fun passwordRecoverByCpf(userCpf: String) {
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

        passwordRecoverTokenService.generateByUserId(userModel.id)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return User(user.username, user.password, listOf(SimpleGrantedAuthority(user.roleName)))
    }
    private fun isCpf(cpf: String) = cpf.isCpf()

    private fun encodePassword(password: String) = passwordEncoder.encode(password)
}
