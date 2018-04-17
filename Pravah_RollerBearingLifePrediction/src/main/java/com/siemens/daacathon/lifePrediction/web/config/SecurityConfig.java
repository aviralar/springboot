package com.siemens.daacathon.lifePrediction.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/", "/home/**").permitAll().and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("remove")
				.invalidateHttpSession(true).logoutSuccessUrl("/");

		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
}