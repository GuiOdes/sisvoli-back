package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.PasswordRecoverRequest
import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.requests.UserUpdateRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
        @RequestHeader("Authorization") authorizationHeader: String?
    ) = userService.doRefreshToken(authorizationHeader, request, response)

    @PostMapping("/new")
    fun save(@RequestBody @Valid userModel: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity(userService.save(userModel), HttpStatus.CREATED)
    }
    @PutMapping("/update")
    fun update(@RequestBody userUpdateRequest: UserUpdateRequest): ResponseEntity<UserResponse> {
        val userDocument = jwtUtil.getUserDocument()
        return ResponseEntity(userService.updateByUserDocument(userUpdateRequest, userDocument), HttpStatus.OK)
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
