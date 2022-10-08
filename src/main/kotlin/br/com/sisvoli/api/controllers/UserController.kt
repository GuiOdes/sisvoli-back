package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.api.responses.UserResponse
import br.com.sisvoli.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService
) {
    @PostMapping("/new")
    fun save(@RequestBody userModel: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity(userService.save(userModel), HttpStatus.CREATED)
    }
}
