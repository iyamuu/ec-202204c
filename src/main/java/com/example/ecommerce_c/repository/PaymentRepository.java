package com.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Payment;

/**
 *  支払情報を操作するRepositoryクラス.
 * 
 * @author daina.teranishi
 *
 */
@Repository
public class PaymentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/** 支払情報のRowMapper */
	private RowMapper<Payment> PAYMENT_ROW_MAPPER = (rs, rowNumber) ->{
		
		Payment payment = new Payment();
		payment.setId(rs.getInt("id"));
		payment.setUserId(rs.getInt("user_id"));
		payment.setPay(rs.getInt("pay"));
		payment.setCardNumber(rs.getString("card_number"));
		payment.setCardExpYear(rs.getString("card_exp_year"));
		payment.setCardExpMonth(rs.getString("card_exp_month"));
		payment.setCardName(rs.getString("card_name"));
		payment.setCardCvv(rs.getString("card_cvv"));
		
		return payment;
		
	};

}
