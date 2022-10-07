package br.com.sisvoli.controllers

import br.com.sisvoli.models.UserModel
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
    fun save(@RequestBody userModel: UserModel): ResponseEntity<Any?> {
        return try {
            ResponseEntity(userService.save(userModel), HttpStatus.CREATED)
        } catch (e: Exception) {
            when (e) {
                else -> ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
            }
        }
    }
}
