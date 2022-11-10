package br.com.sisvoli.validation.validator

import br.com.sisvoli.services.interfaces.UserService
import br.com.sisvoli.validation.annotation.UsernameAvailable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UsernameAvailableValidator(
    private val userService: UserService
) : ConstraintValidator<UsernameAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return !userService.existsByUsername(value)
    }
}
