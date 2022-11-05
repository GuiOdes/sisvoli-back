package br.com.sisvoli.validation.validator

import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.validation.annotation.EmailAvailable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(private val userService: UserService) : ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return userService.existsByEmail(value)
    }
}
