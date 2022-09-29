package br.com.sisvoli.controllers

import br.com.sisvoli.services.UserService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ola")
class Hello(
    val userService: UserService
) {

    @GetMapping
    fun oi(): String {
        logger.info { "assdasd" }

        return "alo"
    }

    @GetMapping("/oi")
    fun teste(): String {
        logger.info { "teste" }
//        userService.save(
//            UserModel(
//                id = null,
//                name = "Usuário Padrão",
//                email = "default1@sisvoli.com.br",
//                password = "123456",
//                cpf = "321.111.111-11",
//                gender = Gender.MALE,
//                birthDate = LocalDate.parse("2000-01-01"),
//                username = "default",
//                roleName = "DEFAULT"
//            )
//        )
        return "oi"
    }

    companion object {
        val logger = KotlinLogging.logger { }
    }
}
