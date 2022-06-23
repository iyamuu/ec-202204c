package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Payment;
import com.example.ecommerce_c.repository.PaymentRepository;

/**
 * 支払情報を操作するサービス.
 * 
 * @author hvthinh
 *
 */
@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment findOneByUserId(Integer userId) {
		return paymentRepository.findOneByUserId(userId);
	}
	
	public Integer insertOne(Payment payment) {
		return paymentRepository.insertOne(payment);
	}
}
