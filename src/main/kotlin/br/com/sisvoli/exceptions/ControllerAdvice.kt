package br.com.sisvoli.exceptions

import br.com.sisvoli.api.responses.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0012).responseEntity()
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleRequestMethodNotSupported(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0011).responseEntity()
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun messageNotReadable(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0013).responseEntity()
    }
}
