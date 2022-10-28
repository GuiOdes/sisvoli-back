package br.com.sisvoli.config.security

import br.com.sisvoli.util.getMillisByMinute
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Date
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(
    private val customAuthenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val userDocument = request.getParameter("userDocument")
        val password = request.getParameter("password")

        val authenticationToken = UsernamePasswordAuthenticationToken(userDocument, password)

        return customAuthenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authentication: Authentication
    ) {
        val user: User = authentication.principal as User
        val algorithm = Algorithm.HMAC256("secret".toByteArray())

        val accessToken = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + getMillisByMinute(TEN_MINUTES)))
            .withIssuer(request.requestURL.toString())
            .withClaim("roles", user.authorities.map { it.authority })
            .sign(algorithm)

        val refreshToken = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + getMillisByMinute(THIRD_MINUTES)))
            .withIssuer(request.requestURL.toString())
            .withClaim("roles", user.authorities.map { it.authority })
            .sign(algorithm)

        val responseAttributes = mutableMapOf<String, String>()
        responseAttributes.put("access_token", accessToken)
        responseAttributes.put("refresh_token", refreshToken)
        responseAttributes.put("userCpf", user.username)
        response.contentType = APPLICATION_JSON_VALUE
        ObjectMapper().writeValue(response.outputStream, responseAttributes)
    }

    companion object {
        const val TEN_MINUTES = 10
        const val THIRD_MINUTES = 30
    }
}
