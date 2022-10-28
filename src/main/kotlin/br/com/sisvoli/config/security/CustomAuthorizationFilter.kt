package br.com.sisvoli.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthorizationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.servletPath.equals("/login") || request.servletPath.equals("/user/refresh-token")) {
            filterChain.doFilter(request, response)
        } else {
            val authorizationHeader = request.getHeader("Authorization")
            if (authorizationHeader.isNullOrBlank().not() && authorizationHeader.startsWith("Bearer ")) {
                val token = authorizationHeader.substring("Bearer ".length)
                val algorithm = Algorithm.HMAC256("secret".toByteArray())
                val verifier = JWT.require(algorithm).build()
                val decodedJWT = verifier.verify(token)
                val username = decodedJWT.subject
                val roles = listOf(
                    decodedJWT.getClaim("roles")
                        .toString()
                        .replace("\"", "")
                        .replace("[", "")
                        .replace("]", "")
                )
                val authorities = mutableListOf<SimpleGrantedAuthority>()
                roles.forEach {
                    authorities.add(
                        SimpleGrantedAuthority(it)
                    )
                }
                val authenticationToken =
                    UsernamePasswordAuthenticationToken(username, null, authorities)
                SecurityContextHolder.getContext().authentication = authenticationToken
                filterChain.doFilter(request, response)
            } else {
                filterChain.doFilter(request, response)
            }
        }
    }
}
