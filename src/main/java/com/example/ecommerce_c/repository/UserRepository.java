package com.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.User;

/**
 * Usersテーブルを操作するRepositoryクラス.
 * 
 * @author daina.teranishi
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	private RowMapper<User> USER_ROW_MAPPER = (rs, rowNumber)->{
		
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipCode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		
		return user;
	};
}
