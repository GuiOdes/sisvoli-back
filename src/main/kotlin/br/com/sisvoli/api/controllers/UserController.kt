package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.PasswordRecoverRequest
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.config.security.CustomAuthenticationFilter
import br.com.sisvoli.exceptions.invalid.InvalidRefreshTokenException
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.util.JWTUtil
import br.com.sisvoli.util.getMillisByMinute
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService,
    val jwtUtil: JWTUtil
) {
    @GetMapping("/refresh-token")
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader.isNullOrBlank().not() && authorizationHeader.startsWith("Bearer ")) {
            val refreshToken = authorizationHeader.substring("Bearer ".length)
            val algorithm = Algorithm.HMAC256("secret".toByteArray())
            val verifier = JWT.require(algorithm).build()
            val decodedJWT = verifier.verify(refreshToken)
            val userDocument = decodedJWT.subject
            val userModel = userService.findByCpf(userDocument)

            val accessToken = JWT.create()
                .withSubject(userModel.username)
                .withExpiresAt(
                    Date(System.currentTimeMillis() + getMillisByMinute(CustomAuthenticationFilter.TEN_MINUTES))
                )
                .withIssuer(request.requestURL.toString())
                .withClaim("roles", userModel.roleName)
                .sign(algorithm)

            val responseAttributes = mutableMapOf<String, String>()
            responseAttributes.put("access_token", accessToken)
            responseAttributes.put("refresh_token", refreshToken)
            responseAttributes.put("userCpf", userModel.cpf)
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            ObjectMapper().writeValue(response.outputStream, responseAttributes)
        } else {
            throw InvalidRefreshTokenException()
        }
    }
    @PostMapping("/new")
    fun save(@RequestBody @Valid userModel: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity(userService.save(userModel), HttpStatus.CREATED)
    }
    @PutMapping("/update")
    fun update(@RequestBody userUpdateRequest: UserUpdateRequest): ResponseEntity<UserResponse> {
        val username = jwtUtil.getUsername()
        return ResponseEntity(userService.updateByUsername(userUpdateRequest, username), HttpStatus.OK)
    }

    @PatchMapping("/password-recover/{cpf}")
    fun passwordRecover(@PathVariable cpf: String) {
        userService.requestPasswordRecoverByCpf(cpf)
    }

    @PostMapping("/password-recover/response")
    fun tokenRecoverValidation(@RequestBody passwordRecoverRequest: PasswordRecoverRequest): Boolean {
        return userService.tokenRecoverValidation(passwordRecoverRequest)
    }

    @PostMapping("/password-recover/update-password")
    fun updatePassword(@RequestBody passwordRecoverRequest: PasswordRecoverRequest) {
        return userService.updatePassword(passwordRecoverRequest)
    }
}
