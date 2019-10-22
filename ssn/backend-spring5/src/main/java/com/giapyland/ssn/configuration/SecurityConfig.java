package com.giapyland.ssn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.giapyland.ssn.SsnApplication;
import com.giapyland.ssn.configuration.security.SsnPasswordEncoder;
import com.giapyland.ssn.configuration.security.SsnUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SsnUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new SsnPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
			.authorizeRequests()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.antMatchers("/registration","/login").permitAll()
			.antMatchers("/", "/**").hasAnyAuthority(SsnApplication.ROLE_USER)
				.and()
			.formLogin().loginPage("/login").permitAll()
				.and()
			.logout().logoutSuccessUrl("/");
	}

}
