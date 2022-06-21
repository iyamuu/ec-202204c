package com.example.ecommerce_c.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FormLoginService formLoginService;

	/** セキュリティの対象外を設定 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web.ignoring().antMatchers("/css/**").antMatchers("/js/**").antMatchers("/img/**").antMatchers("/getItemByPage");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/signup").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login") // ログインボタンのURL
				.failureForwardUrl("/login?error=true").defaultSuccessUrl("/top", false)
				.usernameParameter("email").passwordParameter("password");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(formLoginService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
