package br.com.sisvoli.exceptions

import br.com.sisvoli.api.responses.ErrorResponse
import br.com.sisvoli.api.responses.FieldErrorResponse
import br.com.sisvoli.exceptions.conflict.OptionAlreadyExistsException
import br.com.sisvoli.exceptions.conflict.PasswordRecoverAlreadyExistsException
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotCreatedThePollException
import br.com.sisvoli.exceptions.conflict.UserLoggedDidNotUpdateThePollException
import br.com.sisvoli.exceptions.conflict.VoteAlreadyExistsException
import br.com.sisvoli.exceptions.invalid.InvalidCPFException
import br.com.sisvoli.exceptions.invalid.InvalidEndDateException
import br.com.sisvoli.exceptions.invalid.InvalidPollCancelRequest
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledException
import br.com.sisvoli.exceptions.invalid.InvalidPollNotScheduledUpdateException
import br.com.sisvoli.exceptions.invalid.InvalidRefreshTokenException
import br.com.sisvoli.exceptions.invalid.InvalidTokenException
import br.com.sisvoli.exceptions.notFound.CityNotFoundException
import br.com.sisvoli.exceptions.notFound.RecoverTokenNotFoundException
import br.com.sisvoli.exceptions.notFound.StateNotFoundException
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import com.auth0.jwt.exceptions.TokenExpiredException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
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
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException
    (ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ErrorMessages.PS_0016.httpCode,
            ErrorMessages.PS_0016.message,
            ErrorMessages.PS_0016.code,
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.field, it.defaultMessage ?: "Invalid") }
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(InvalidRefreshTokenException::class)
    fun invalidRefreshTokenException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0019).responseEntity()
    }
    @ExceptionHandler(TokenExpiredException::class)
    fun tokenExpiredException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0020).responseEntity()
    }
    @ExceptionHandler(InvalidPollCancelRequest::class)
    fun invalidPollCancelRequest(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0021).responseEntity()
    }
    @ExceptionHandler(InvalidEndDateException::class)
    fun invalidEndDataException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0022).responseEntity()
    }
    @ExceptionHandler(UserLoggedDidNotCreatedThePollException::class)
    fun invalidUserLoggedDidNotCreatAPoll(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0023).responseEntity()
    }
    @ExceptionHandler(OptionAlreadyExistsException::class)
    fun optionAlreadyExistsException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0024).responseEntity()
    }
    @ExceptionHandler(InvalidPollNotScheduledException::class)
    fun invalidPollNotScheduledException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0025).responseEntity()
    }
    @ExceptionHandler(InvalidPollNotScheduledUpdateException::class)
    fun invalidPollNotScheduledUpdateException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0026).responseEntity()
    }
    @ExceptionHandler(UserLoggedDidNotUpdateThePollException::class)
    fun userLoggedDidNotUpdateThePollException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0027).responseEntity()
    }
    @ExceptionHandler(VoteAlreadyExistsException::class)
    fun voteAlreadyExistsException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0028).responseEntity()
    }
    @ExceptionHandler(StateNotFoundException::class)
    fun stateNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        return ErrorResponse.of(ErrorMessages.PS_0029).responseEntity()
    }
}
