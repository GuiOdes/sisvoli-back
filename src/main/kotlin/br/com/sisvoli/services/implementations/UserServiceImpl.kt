package br.com.sisvoli.services.implementations

import br.com.sisvoli.api.requests.MailRequest
import br.com.sisvoli.api.requests.PasswordRecoverRequest
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.database.repositories.interfaces.UserRepository
import br.com.sisvoli.enums.RoleEnum
import br.com.sisvoli.exceptions.conflict.PasswordRecoverAlreadyExistsException
import br.com.sisvoli.exceptions.invalid.InvalidRefreshTokenException
import br.com.sisvoli.exceptions.invalid.InvalidTokenException
import br.com.sisvoli.models.UserModel
import br.com.sisvoli.services.interfaces.EmailService
import br.com.sisvoli.services.interfaces.PasswordRecoverTokenService
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.util.getMillisByMinute
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.transaction.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val passwordRecoverTokenService: PasswordRecoverTokenService,
    private val emailService: EmailService
) : UserDetailsService, UserService {

    override fun doRefreshToken(
        authorizationHeader: String?,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        if (isValidAuthorizationHeader(authorizationHeader)) {
            val refreshToken = authorizationHeader!!.substring("Bearer ".length)
            val algorithm = Algorithm.HMAC256("secret".toByteArray())
            val verifier = JWT.require(algorithm).build()
            val decodedJWT = verifier.verify(refreshToken)
            val userDocument = decodedJWT.subject
            val userModel = findByCpf(userDocument)

            val accessToken = generateToken(userModel, request, algorithm, AUTH_TOKEN_EXPIRATION_MINUTES)
            val newRefreshToken = generateToken(userModel, request, algorithm, REFRESH_TOKEN_EXPIRATION_MINUTES)

            val responseAttributes = mutableMapOf<String, String>()
            responseAttributes.put("access_token", accessToken)
            responseAttributes.put("refresh_token", newRefreshToken)
            responseAttributes.put("userCpf", userModel.cpf)
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            ObjectMapper().writeValue(response.outputStream, responseAttributes)
        } else {
            throw InvalidRefreshTokenException()
        }
    }

    private fun isValidAuthorizationHeader(authorizationHeader: String?) =
        authorizationHeader.isNullOrBlank().not() && authorizationHeader!!.startsWith("Bearer ")

    private fun generateToken(
        userModel: UserModel,
        request: HttpServletRequest,
        algorithm: Algorithm?,
        minutesExpiration: Int
    ) = JWT.create()
        .withSubject(userModel.cpf)
        .withExpiresAt(
            Date(System.currentTimeMillis() + getMillisByMinute(minutesExpiration))
        )
        .withIssuer(request.requestURL.toString())
        .withClaim("role", userModel.roleName)
        .sign(algorithm)

    override fun save(userRequest: UserRequest): UserResponse {
        logger.info { "Starting to save a user with document: ${userRequest.cpf}" }
        val userModel = userRequest.toUserModel(RoleEnum.DEFAULT.name).copy(
            password = encodePassword(userRequest.password),
            cpf = userRequest.cpf
                .replace("-", "")
                .replace(".", "")
        )
        return userRepository.save(userModel).toUserResponse()
    }

    override fun findByUsername(username: String): UserModel {
        return userRepository.findByUsername(username)
    }

    @Transactional
    override fun requestPasswordRecoverByCpf(userCpf: String): UUID {
        logger.info { "Starting to request a password reset for user with document: $userCpf..." }
        val userModel = userRepository.findByCpf(userCpf)

        logger.info { "Checking if exists a recovery token from user with document $userCpf..." }
        val recoverTokenModel = userRepository.findRecoverTokenByUserId(userModel.id!!)

        if (recoverTokenModel != null) {
            logger.warn {
                "A token from user #${userModel.id} exists! Checking if this token is older than" +
                    "24 hours..."
            }
            val dayAfterRecoverCreation = recoverTokenModel.creationDate!!.plusDays(1)
            val hourDifference = ChronoUnit.HOURS.between(LocalDateTime.now(), dayAfterRecoverCreation)

            if (hourDifference > 0) {
                logger.warn {
                    "An active token exists from user #${userModel.id}! A new token can be generated " +
                        "in $hourDifference hours!"
                }
                throw PasswordRecoverAlreadyExistsException(hourDifference)
            }

            passwordRecoverTokenService.deleteById(recoverTokenModel.id!!)
        }

        logger.info { "Doesn't exists a token from user #${userModel.id}. Generating a new token..." }
        val token = passwordRecoverTokenService.generateByUserId(userModel.id).token

        logger.info { "Sending mail with password recovery token to ${userModel.email}" }
        emailService.sendMail(
            MailRequest(
                emailTo = userModel.email,
                subject = "Recuperação de senha",
                text = "Olá, ${userModel.username}! O seu token para recuperação de senha é $token."
            )
        )
        return userModel.id
    }

    override fun updateByUserDocument(userUpdateRequest: UserUpdateRequest, userDocument: String): UserResponse {
        val userModel = findByCpf(userDocument)
        logger.info { "Starting to update user #${userModel.id} data." }
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

    override fun tokenRecoverValidation(passwordRecoverRequest: PasswordRecoverRequest): Boolean {
        logger.info { "Validating recovery token..." }
        return passwordRecoverTokenService.validateByUserDocument(
            passwordRecoverRequest.cpf,
            passwordRecoverRequest.token
        )
    }

    @Transactional
    override fun updatePassword(passwordRecoverRequest: PasswordRecoverRequest) {
        logger.info { "Starting to update password of user with document ${passwordRecoverRequest.cpf}" }
        val isValidToken = passwordRecoverTokenService.validateByUserDocument(
            passwordRecoverRequest.cpf,
            passwordRecoverRequest.token
        )

        if (isValidToken.not()) {
            throw InvalidTokenException()
        }

        updateByUserDocument(
            UserUpdateRequest(password = passwordRecoverRequest.newPassword),
            passwordRecoverRequest.cpf
        )

        passwordRecoverTokenService.deleteByTokenAndUserDocument(
            passwordRecoverRequest.token,
            passwordRecoverRequest.cpf
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByCpf(username)
        return User(user.cpf, user.password, listOf(SimpleGrantedAuthority(user.roleName)))
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findByCpf(cpf: String): UserModel {
        return userRepository.findByCpf(cpf)
    }

    override fun existsByCpf(cpf: String): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    private fun encodePassword(password: String) = passwordEncoder.encode(password)

    companion object {
        private const val AUTH_TOKEN_EXPIRATION_MINUTES = 10
        private const val REFRESH_TOKEN_EXPIRATION_MINUTES = 30
        private val logger = KotlinLogging.logger { }
    }
}
