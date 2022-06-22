package com.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.GiftInformation;

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
}
