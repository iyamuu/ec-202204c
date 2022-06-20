package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.UserRepository;

/**
 * 新規登録処理を行うServiceクラス.
 * 
 * @author daina.teranishi
 *
 */
@Service
@Transactional
public class SignupService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 同じメールアドレスが存在していないかを確認する.
	 * 
	 * @param email 確認するメールアドレス
	 * @return 存在していればそのUserクラス、存在していなければnull
	 */
	public User checkSameMailAddress(String email) {
		
		return userRepository.findByMailAddress(email);
	}
	
	/**
	 * ユーザを新規登録する.
	 * 
	 * @param user 登録したいユーザ情報、idはnull
	 * @return idが付与されたユーザ情報
	 */
	public User registerUser(User user) {
		
		Integer id = userRepository.insertOne(user);
		
		user.setId(id);
		
		return user;
	}
}
