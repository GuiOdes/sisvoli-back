package br.com.sisvoli.exceptions

import org.springframework.http.HttpStatus

enum class ErrorMessages(val httpCode: Int, val code: String, val message: String) {
    PS_0001(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0001",
        "CPF, telefone ou email já cadastrado no sistema"
    ),
    PS_0002(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0002",
        "Email de usuário inválido"
    ),
    PS_0003(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0003",
        "Email ou senha inválidos"
    ),
    PS_0004(
        HttpStatus.NOT_FOUND.value(),
        "PS-0004",
        "Usuário não encontrado"
    ),
    PS_0005(
        HttpStatus.NOT_FOUND.value(),
        "PS-0005",
        "CEP não encontrado"
    ),
    PS_0006(
        HttpStatus.CONFLICT.value(),
        "PS-0006",
        "Nome de usuário já cadastrado"
    ),
    PS_0007(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0007",
        "Gênero inválido"
    ),
    PS_0008(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0008",
        "Data de votação inválida"
    ),
    PS_0009(
        HttpStatus.NOT_ACCEPTABLE.value(),
        "PS-0009",
        "Usuário não deve votar na própria enquete"
    ),
    PS_0010(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0010",
        "Opção de votação inválida"
    ),
    PS_0011(
        HttpStatus.METHOD_NOT_ALLOWED.value(),
        "PS-0011",
        "Método de requisição não suportado"
    ),
    PS_0012(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "PS-0012",
        "Erro desconhecido - Contate um administrador"
    ),
    PS_0013(
        HttpStatus.BAD_REQUEST.value(),
        "PS-0013",
        "Leitura de requisição ilegível para a API"
    ),
    PS_0014(
        HttpStatus.CONFLICT.value(),
        "PS-0014",
        "Voto já computado para a enquete em questão"
    )
}
