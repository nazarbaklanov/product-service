package com.example.productservice.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    @Value("\${server.basic.auth.username}")
    private val username: String,
    @Value("\${server.basic.auth.password}")
    private val password: String,
    private val passwodrEncoder: ApplicationConfig
): WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and().httpBasic()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(authentication: AuthenticationManagerBuilder) {
        authentication.inMemoryAuthentication()
            .withUser(username)
            .password(passwodrEncoder.passwordEncoder().encode(password))
            .authorities("ROLE_USER")
    }
}