package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Payment;

/**
 * 絞り込み情報を操作する.
 * 
 * @author daina.teranishi
 *
 */
@Repository
public class GifInformationRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private RowMapper<GiftInformation> GIFT_INFO_ROW_MAPPER = (rs, rowNumber) -> {
		GiftInformation information = new GiftInformation();
		information.setId(rs.getInt("id"));
		information.setUserId(rs.getInt("user_id"));
		information.setLowerAge(rs.getInt("lower_age"));
		information.setUpperAge(rs.getInt("upper_age"));
		information.setGender(rs.getString("gender"));
		information.setLowerBudget(rs.getInt("lower_budget"));
		information.setUpperBudger(rs.getInt("upper_budget"));
		
		return information;
	};
	
	
	/**
	 * ユーザIDから絞り込み情報を取得する.
	 * 
	 * 
	 * @param userId ユーザID
	 * @return　絞り込み情報
	 */
	public GiftInformation findOneByUserId(Integer userId) {
		
		String sql = "Select id, user_id, lower_age, upper_age, gender, lower_budget, upper_budget From gift_informations Where user_id=:userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		
		List<GiftInformation> infoList = jdbcTemplate.query(sql, param,GIFT_INFO_ROW_MAPPER);
		
		if(infoList.size() == 0) {
			return null;
		}
		
		
		//HACK 複数件の宛先情報があれば不具合がある
		return infoList.get(0);
	}
	
	/**
	 * 絞り込み情報を登録する.
	 * 
	 * @param info 登録する絞り込み情報　（IDはnull）
	 * @return 絞り込み情報　（IDはsetされている）
	 */
	public GiftInformation insertOne(GiftInformation info) {
		
		String sql = "Insert Into addressees(user_id, lower_age, upper_age, gender, lower_budget, upper_budget)"
					+ "Values(:userId, :lowerAge, :upperAge, :gender, :lowerBudget, :upperBudget) Returning id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(info);
		
		Integer id = jdbcTemplate.queryForObject(sql, param, Integer.class); 
		info.setId(id);
		
		return info;
	}
}
