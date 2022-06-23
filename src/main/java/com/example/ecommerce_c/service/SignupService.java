package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_c.domain.Addressee;
import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Payment;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.AddresseeRepository;
import com.example.ecommerce_c.repository.GifInformationRepository;
import com.example.ecommerce_c.repository.PaymentRepository;
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
	@Autowired
	private AddresseeRepository addresseeRepository;
	@Autowired
	private GifInformationRepository gifInformationRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	public User registerUser(User user, Addressee addressee, GiftInformation giftInformation, Payment payment) {
		
		//ユーザ登録
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Integer userId = userRepository.insertOne(user);
		user.setId(userId);
		
		//宛先情報登録
		addressee.setUserId(userId);
		Integer addresseeId = addresseeRepository.insertOne(addressee);
		addressee.setId(addresseeId);
		
		//絞り込み情報登録
		giftInformation.setUserId(userId);
		Integer giftInfoId = gifInformationRepository.insertOne(giftInformation);
		giftInformation.setId(giftInfoId);
		
		//支払い情報登録
		payment.setUserId(userId);
		Integer paymentId = paymentRepository.insertOne(payment);
		payment.setId(paymentId);
		
		return user;
	}
}
