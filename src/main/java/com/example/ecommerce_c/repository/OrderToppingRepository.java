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
//	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = (rs, i) -> {
//		OrderTopping orderTopping = new OrderTopping();
//		orderTopping.setId(rs.getInt("O.id"));
//		orderTopping.setToppingId(rs.getInt("O.topping_id"));
//		orderTopping.setOrderItemId(rs.getInt("O.order_item_id"));
//		
//		Topping topping = new Topping();
//		topping.setId(rs.getInt("t.id"));
//		topping.setName(rs.getString("t.name"));
//		topping.setPriceM(rs.getInt("t.price_m"));
//		topping.setPriceL(rs.getInt("t.price_l"));
//		
//		orderTopping.setTopping(topping);;
//		
//		return orderTopping;
//	};

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
