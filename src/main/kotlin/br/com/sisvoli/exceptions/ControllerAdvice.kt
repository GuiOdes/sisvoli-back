package br.com.sisvoli.exceptions

import br.com.sisvoli.api.responses.ErrorResponse
import br.com.sisvoli.exceptions.conflict.PasswordRecoverAlreadyExistsException
import br.com.sisvoli.exceptions.invalid.InvalidCPFException
import br.com.sisvoli.exceptions.invalid.InvalidTokenException
import br.com.sisvoli.exceptions.notFound.CityNotFoundException
import br.com.sisvoli.exceptions.notFound.RecoverTokenNotFoundException
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import org.springframework.http.HttpStatus
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

    @ExceptionHandler(InvalidCPFException::class)
    fun invalidCPFException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0015).responseEntity()
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0004).responseEntity()
    }

    @ExceptionHandler(CityNotFoundException::class)
    fun cityNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0005).responseEntity()
    }

    @ExceptionHandler(PasswordRecoverAlreadyExistsException::class)
    fun passwordRecoverAlreadyExistsException(
        ex: PasswordRecoverAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        return ErrorResponse(
            httpCode = HttpStatus.CONFLICT.value(),
            message = ex.message,
            internalCode = "PS-0016",
        ).responseEntity()
    }

    @ExceptionHandler(RecoverTokenNotFoundException::class)
    fun recoverTokenNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0017).responseEntity()
    }

    @ExceptionHandler(InvalidTokenException::class)
    fun invalidTokenException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0018).responseEntity()
    }
}
