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
import com.example.ecommerce_c.form.OrderItemForm;

@Repository
public class OrderItemRepository {
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs,i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		
		return orderItem;
		
	};
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public OrderItem findOrderItemById (Integer orderItemId) {
		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		List<OrderItem> orderItemList  =jdbcTemplate.query(sql, param, ORDER_ITEM_ROW_MAPPER );
		OrderItem orderItem = orderItemList.get(0);
		return orderItem;
	}
	
	public Integer insertOne(OrderItem orderItem) {
		String sql = "Insert into order_items(item_id, order_id, quantity, size) values (:itemId, :orderId, :quantity, :size) return id ";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}
	
	
	

}
