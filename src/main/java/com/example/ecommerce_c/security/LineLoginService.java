package com.example.ecommerce_c.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.UserRepository;

/**
 * LineでのLogin処理を行うServiceクラス.
 * 
 * @author teranishidaina
 *
 */
@Service
public class LineLoginService extends DefaultOAuth2UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oath2User = super.loadUser(userRequest);
		//Line IDのセット
		String lineId = (String)oath2User.getAttributes().get("userId");
		
		
		User user = userRepository.findByLineId(lineId);
		if(user == null) {
			user = new User();
			user.setLineId(lineId);
			user.setName(oath2User.getName());
			user.setPassword("password");  //仮のパスワード
			user.setId(-1);              //アカウント登録まだなら負の値
			System.out.println(user);
		}
		
		// 権限付与の例
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザ権限付与
		
		return new LoginUser(user, authorityList);
		
	}

}
