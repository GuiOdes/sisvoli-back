package br.com.sisvoli.validation

import br.com.sisvoli.services.interfaces.UserService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(private val userService: UserService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()){
            return false
        }
        return userService.emailAvailable(value)
    }
}
