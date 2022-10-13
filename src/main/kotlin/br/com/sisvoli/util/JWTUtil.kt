package br.com.sisvoli.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class JWTUtil {

    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal as String
    }
}
