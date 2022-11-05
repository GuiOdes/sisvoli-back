package br.com.sisvoli.validation.validator

import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.validation.annotation.CpfAvailable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CpfAvailableValidator(private val userService: UserService) : ConstraintValidator<CpfAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return !userService.existsByCpf(value)
    }
}
