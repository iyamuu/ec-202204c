package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

	private RowMapper<User> USER_ROW_MAPPER = (rs, rowNumber) -> {

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

	/**
	 * emailからUserを検索する.
	 * 
	 * @param email 検索したいemail
	 * @return　該当するUser、存在しなければnull
	 */
	public User findByMailAddress(String email) {

		String sql = "Select id, name, email, password, zipcode, address, telephone "
					+ "From users Where email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		
		List<User> userList = jdbcTemplate.query(sql, param, USER_ROW_MAPPER);
		
		if(userList.size() == 0) {
			return null;
		}
		
		return userList.get(0);
	}
}
