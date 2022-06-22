package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.OrderTopping;
import com.example.ecommerce_c.domain.Topping;

/**
 * 注文トッピング情報を扱うリポジトリ.
 * 
 * @author hvthinh
 *
 */

@Repository
public class OrderToppingRepository {
	/**
	 * 注文トッピングのローマッパー.
	 */

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文トッピング情報を格納する.
	 * 
	 * @param orderItem
	 * @return
	 */
	public Integer insertOrderTopping(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings(topping_id,order_item_id) values (:toppingId, :orderItemId) Returning id;";

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		Integer id = template.queryForObject(sql, param, Integer.class);

		return id;
	}
}
