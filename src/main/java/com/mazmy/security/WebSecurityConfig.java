package com.mazmy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author azmym
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private final DriverAuthenticationProvider driverAuthenticationProvider;
	
	@Autowired
	public WebSecurityConfig(final DriverAuthenticationProvider driverAuthenticationProvider) {
		this.driverAuthenticationProvider = driverAuthenticationProvider;
	}
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(driverAuthenticationProvider);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.httpBasic().and().csrf().disable().authorizeRequests()
			.antMatchers("/v1/**").authenticated()
			.anyRequest().permitAll();
	}
}
