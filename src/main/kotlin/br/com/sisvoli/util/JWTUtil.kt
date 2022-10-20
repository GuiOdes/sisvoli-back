package br.com.sisvoli.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class JWTUtil {
    private fun getContext() = SecurityContextHolder.getContext()

    fun getUsername(): String {
        return getContext().authentication.principal as String
    }
}
