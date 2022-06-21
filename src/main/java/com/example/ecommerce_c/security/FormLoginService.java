package com.example.ecommerce_c.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.UserRepository;

/**
 * フォームからの認証用のServiceクラス.
 * 
 * @author daina.teranishi
 *
 */
public class FormLoginService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 *メールアドレスでの認証を行う.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		User user = userRepository.findByMailAddress(email);
		if(user == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));   //ユーザ権限の付与
		
		return new LoginUser(user, authorityList);
	}

}
