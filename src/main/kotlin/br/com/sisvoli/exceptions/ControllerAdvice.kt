package br.com.sisvoli.exceptions

import br.com.sisvoli.api.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            400,
            "Erro desconhecido",
            "PS-0012"
        )

        return ResponseEntity(error, HttpStatus.valueOf(error.httpCode))
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleRequestMethodNotSupported(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            405,
            "Método de requisição não suportado",
            "PS-0011"
        )

        return ResponseEntity(error, HttpStatus.valueOf(error.httpCode))
    }

}