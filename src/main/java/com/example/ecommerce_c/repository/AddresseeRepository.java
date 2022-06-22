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

/**
 * 宛先情報を操作するRepositoryクラス.
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
	
	
	/**
	 * ユーザIDから宛先情報を取得する.
	 * 
	 * 
	 * @param userId ユーザID
	 * @return　宛先情報
	 */
	public Addressee findOneByUserId(Integer userId) {
		
		String sql = "Select id, user_id, name, zipcode, address, telephone From addressees Where user_id=:userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		
		List<Addressee> addresseeList = jdbcTemplate.query(sql, param, ADDRESSEE_ROW_MAPPER);
		
		if(addresseeList.size() == 0) {
			return null;
		}
		
		
		//HACK 複数件の宛先情報があれば不具合がある
		return addresseeList.get(0);
	}
	
	/**
	 * 宛先情報を登録する.
	 * 
	 * @param addressee 登録する宛先情報　（IDはnull）
	 * @return 宛先情報　（IDはsetされている）
	 */
	public Addressee insertOne(Addressee addressee) {
		
		String sql = "Insert Into addressees(user_id, name, zipcode, address, telephone)"
					+ "Values(:userId, :name, :zipCode, :address, :telephone) Returning id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(addressee);
		
		Integer id = jdbcTemplate.queryForObject(sql, param, Integer.class); 
		addressee.setId(id);
		
		return addressee;
	}
}
