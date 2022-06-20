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
import com.example.ecommerce_c.form.OrderItemForm;
import com.example.ecommerce_c.form.OrderToppingForm;

/**
 * 注文トッピング情報を扱うリポジトリ.
 * 
 * @author hvthinh
 *
 */

@Repository
public class OrderToppingRepository {
//	private static final RowMapper<OrderTopping> ORDERTOPPING_ROW_MAPPER = (rs, i) -> {
//		OrderTopping orderTopping = new OrderTopping();
//		orderTopping.setId(rs.getInt("id"));
//		orderTopping.setToppingId(rs.getInt("topping_id"));
//		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
//		
//		return orderTopping;
//	};
	
	@Autowired 
	private NamedParameterJdbcTemplate template;
	
	public Integer insertOrderTopping(OrderItemForm orderItemForm){
		String sql = "INSERT INTO order_toppings(topping_id,order_item_id) values (:toppingId, :orderItemId) return id;";
		Integer id = null;
		for ( Integer toppingId : orderItemForm.getToppingIdList()) {
			SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", toppingId);
			id = template.queryForObject(sql, param, Integer.class);
		}
		
		return id;
	}
}
