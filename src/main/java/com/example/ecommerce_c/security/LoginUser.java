package com.example.ecommerce_c.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.ecommerce_c.domain.User;

public class LoginUser implements UserDetails, OAuth2User {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private final Collection<GrantedAuthority> authorities;
	
	public LoginUser(User user, Collection<GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getUserId() {
		return user.getId();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public String getTelephone() {
		return user.getTelephone();
	}
	
	public String getLineId() {
		return user.getLineId();
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getName();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
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


	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "test";
	}


	@Override
	public String toString() {
		return "LoginUser [user=" + user + ", authorities=" + authorities + "]";
	}

}
