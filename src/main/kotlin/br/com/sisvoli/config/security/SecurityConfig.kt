@file:Suppress("DEPRECATION")

package br.com.sisvoli.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        val customAuthenticationFilter = CustomAuthenticationFilter(authenticationManagerBean())

        http.csrf().disable()

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .antMatchers("/login", "/user/password-recover/**", "/user/refresh-token")
            .permitAll()
            .antMatchers("/user/new")
            .permitAll()
            .anyRequest()
            .authenticated()

        // Verificação de role ao acessar endpoint
        // http.authorizeRequests().antMatchers("/ola/oi").hasAnyAuthority("ADMIN")
        // http.authorizeRequests().antMatchers("/ola").hasAnyAuthority("DEFAULT")

        http.addFilter(customAuthenticationFilter)

        http.addFilterBefore(CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}
