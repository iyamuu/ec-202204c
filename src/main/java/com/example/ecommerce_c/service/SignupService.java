package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
}
