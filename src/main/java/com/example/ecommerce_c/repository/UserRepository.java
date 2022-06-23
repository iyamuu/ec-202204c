package com.example.ecommerce_c.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		user.setTelephone(rs.getString("telephone"));
		user.setLineId(rs.getString("line_id"));

		return user;
	};
	
	/**
	 * idからUserを検索する.
	 * 
	 * @param id 検索したいID
	 * @return　該当するUser、存在しなければnull
	 */
	public User findById(int id) {
		String sql = "Select id, name, email, password, telephone, line_id "
				+ "From users Where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		List<User> userList = jdbcTemplate.query(sql, param, USER_ROW_MAPPER);
		
		if(userList.size() == 0) {
			return null;
		}
		
		return userList.get(0);
		
	}

	/**
	 * emailからUserを検索する.
	 * 
	 * @param email 検索したいemail
	 * @return 該当するUser、存在しなければnull
	 */
	public User findByMailAddress(String email) {

		String sql = "Select id, name, email, password, telephone, line_id "
				+ "From users Where email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> userList = jdbcTemplate.query(sql, param, USER_ROW_MAPPER);

		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}
	
	/**
	 * Line idからUserを検索する.
	 * 
	 * @param lineId 検索したいline id
	 * @return 該当するUser、存在しなければnull
	 */
	public User findByLineId(String lineId) {

		String sql = "Select id, name, email, password, telephone, line_id "
				+ "From users Where line_id=:lineId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("lineId", lineId);

		List<User> userList = jdbcTemplate.query(sql, param, USER_ROW_MAPPER);

		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

	
	/**
	 * メールアドレスとパスワードから検索を行う（ログイン処理用）
	 *  
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　該当するUser、存在しなければnull
	 */
	public User findByMailAddressAndPassword(String email, String password) {

		String sql = "Select id, name, email, password, telephone, line_id "
				+ "From users Where email=:email ANd password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);

		List<User> userList = jdbcTemplate.query(sql, param, USER_ROW_MAPPER);

		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);

	}

	/**
	 * Usersテーブルに新規追加をする.
	 * 
	 * @param user 追加したいUserドメイン、idはnull
	 * @return 自動採番されたユーザID
	 */
	public Integer insertOne(User user) {

		String sql = "Insert Into users(name, email, password, telephone, line_id) "
				+ "Values(:name, :email, :password, :telephone, lineId) Returning id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		Integer userid = jdbcTemplate.queryForObject(sql, param, Integer.class);

		return userid;
	}
}
