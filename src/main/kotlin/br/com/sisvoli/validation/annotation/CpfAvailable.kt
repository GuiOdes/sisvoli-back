package br.com.sisvoli.validation.annotation

import br.com.sisvoli.validation.validator.CpfAvailableValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CpfAvailableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class CpfAvailable(
    val message: String = "Cpf já cadastrado",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
