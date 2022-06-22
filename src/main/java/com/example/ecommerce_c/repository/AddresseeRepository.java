package com.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Addressee;

/**
 * 宛先情報
 * 
 * @author daina.teranishi
 *
 */
@Repository
public class AddresseeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/** 宛先情報用のRowMapper */
	private RowMapper<Addressee> ADDRESSEE_ROW_MAPPER = (rs, rowNumber) -> {
		Addressee addressee = new Addressee();
		addressee.setId(rs.getInt("id"));
		addressee.setUserId(rs.getInt("user_id"));
		addressee.setName(rs.getString("name"));
		addressee.setZipCode(rs.getString("zipcode"));
		addressee.setAddress(rs.getString("address"));
		addressee.setTelephone(rs.getString("telephone"));
		
		return addressee;
		
	};
}
