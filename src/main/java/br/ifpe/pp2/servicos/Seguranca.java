package br.ifpe.pp2.servicos;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class Seguranca {
    
	protected void configure(final HttpSecurity http) throws Exception {
	    http
	        .formLogin()
	        .loginPage("/login")
	        .failureUrl("/")
	      .and()
	        .logout()
	        .logoutSuccessUrl("/loginn");
 
}
}