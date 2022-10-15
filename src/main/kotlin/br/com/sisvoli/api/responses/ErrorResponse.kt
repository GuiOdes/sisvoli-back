package br.com.sisvoli.api.responses

import br.com.sisvoli.exceptions.ErrorMessages
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ErrorResponse(
    val httpCode: Int,
    val message: String,
    val internalCode: String,
    val errorList: List<FieldErrorResponse>? = null
) {

    fun responseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity(this, HttpStatus.valueOf(this.httpCode))
    companion object {
        fun of(
            errorEnum: ErrorMessages,
            errorList: List<FieldErrorResponse>? = null
        ): ErrorResponse {
            return ErrorResponse(
                httpCode = errorEnum.httpCode,
                message = errorEnum.message,
                internalCode = errorEnum.code,
                errorList
            )
        }
    }
}
