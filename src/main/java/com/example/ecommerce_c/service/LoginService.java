package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.UserRepository;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * ログイン処理を行う.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　ログインユーザ
	 */
	public User login(String email, String password) {
		
		return userRepository.findByMailAddressAndPassword(email, password);
	}
}
