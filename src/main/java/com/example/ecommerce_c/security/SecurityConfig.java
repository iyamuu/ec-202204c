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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FormLoginService formLoginService;

	/** セキュリティの対象外を設定 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web.ignoring().antMatchers("/css/**").antMatchers("/js/**").antMatchers("/img/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/signup").permitAll()
				.antMatchers("/top").permitAll().antMatchers("/cart").permitAll() // 一覧画面とカート画面を許可
				.antMatchers("/getItemByPage").permitAll()
				.antMatchers("/show").permitAll()
				.antMatchers("/add").permitAll().antMatchers("/delete").permitAll().antMatchers("/update").permitAll() // RestControllerへも許可
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").loginProcessingUrl("/login") // ログインボタンのURL
				.failureForwardUrl("/login?error=true").defaultSuccessUrl("/top", true).usernameParameter("email")
				.passwordParameter("password")
				.and().oauth2Login().loginPage("/login") // Line Login
				;

		
		//cookieにCSRFトークンを入れる
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		// ログアウト
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(formLoginService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean // Line認証プロバイダのBean定義
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(ClientRegistration.withRegistrationId("line")
				.clientId("1657245742").clientSecret("9956e938e9df0ab9516829d013bf4ea0")
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).scope("profile").clientName("LINE")
				.authorizationUri("https://access.line.me/oauth2/v2.1/authorize")
				.tokenUri("https://api.line.me/oauth2/v2.1/token").jwkSetUri("https://api.line.me/oauth2/v2.1/certs")
				.userInfoUri("https://api.line.me/v2/profile").userNameAttributeName("userId")
				.redirectUri("https://ec-202204c-toy.herokuapp.com/ec-202204c/oauth2/code/line").build());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
