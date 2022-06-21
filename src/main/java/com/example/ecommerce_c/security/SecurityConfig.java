package com.example.ecommerce_c.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private FormLoginService formLoginService;
	
	/** セキュリティの対象外を設定 */
	public void cofigure(WebSecurity web) throws Exception{
		
		web.ignoring().antMatchers("/js/**").antMatchers("/css/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests().antMatchers("/login").permitAll()
			.antMatchers("/signup").permitAll().anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").loginProcessingUrl("/login")  //ログインボタンのURL
			.failureForwardUrl("/login?error=true")
			.defaultSuccessUrl("/login", false);
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(formLoginService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
