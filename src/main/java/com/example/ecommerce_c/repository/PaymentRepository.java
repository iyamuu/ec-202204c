package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Addressee;
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
	
	/**
	 * ユーザIDから支払情報を取得する.
	 * 
	 * 
	 * @param userId ユーザID
	 * @return　支払情報
	 */
	public Payment findOneByUserId(Integer userId) {
		
		String sql = "Select id, user_id, pay, card_number, card_exp_year, card_exp_month, card_name, card_cvv From payments Where user_id=:userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		
		List<Payment> paymentList = jdbcTemplate.query(sql, param,PAYMENT_ROW_MAPPER);
		
		if(paymentList.size() == 0) {
			return null;
		}
		
		
		//HACK 複数件の宛先情報があれば不具合がある
		return paymentList.get(0);
	}
	
	/**
	 * 支払情報を登録する.
	 * 
	 * @param apayment 登録する支払情報　（IDはnull）
	 * @return 支払情報　（IDはsetされている）
	 */
	public Payment insertOne(Payment payment) {
		
		String sql = "Insert Into addressees(user_id, pay, card_number, card_exp_year, card_exp_month, card_name, card_cvv)"
					+ "Values(:userId, :pay, :cardNumber, :cardExpYear, :cardExpMonth, :cardName, :cardCvv) Returning id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(payment);
		
		Integer id = jdbcTemplate.queryForObject(sql, param, Integer.class); 
		payment.setId(id);
		
		return payment;
	}

}
