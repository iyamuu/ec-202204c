package com.example.ecommerce_c.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommerce_c.domain.User;

public class LoginUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private final Collection<GrantedAuthority> authorities;
	
	public LoginUser(User user, Collection<GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getName();
	}
	
	
	
	/**　有効期限が切れた場合などの設定、とりあえずすべてのアカウントは有効 */
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
