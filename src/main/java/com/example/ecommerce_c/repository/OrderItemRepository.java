package com.example.ecommerce_c.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.OrderTopping;
import com.example.ecommerce_c.form.OrderItemForm;

@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	public OrderItem findOrderItemById (Integer orderItemId) {
//		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE id = :orderItemId";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
////		List<OrderItem> orderItemList  =jdbcTemplate.query(sql, param, ORDER_ITEM_ROW_MAPPER );
//		OrderItem orderItem = orderItemList.get(0);
//		return orderItem;
//	}
//	
	/**
	 * 注文商品情報を格納する.
	 * 
	 * @param orderItem
	 * @return 注文商品Id
	 */
	public Integer insertOne(OrderItem orderItem) {
		String sql = "Insert into order_items(item_id, order_id, quantity, size) values (:itemId, :orderId, :quantity, :size) return id ";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}

	/**
	 * 注文商品を削除する.
	 * 
	 * @param orderItemId
	 */
	public void deleteOrderItem(Integer orderItemId) {
		String sql = "DELETE FROM order_items where id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		jdbcTemplate.update(sql, param);
	}

}
