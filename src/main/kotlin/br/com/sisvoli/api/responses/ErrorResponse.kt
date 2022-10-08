package br.com.sisvoli.api.responses

data class ErrorResponse(
    val httpCode: Int,
    val message: String,
    val internalCode: String,
    val errorList: List<FieldErrorResponse>? = null
)