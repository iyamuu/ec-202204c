package com.example.ecommerce_c.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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

	private static RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size").toCharArray()[0]);

		return orderItem;
	};
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * 注文商品情報を格納する.
	 * 
	 * @param orderItem
	 * @return 注文商品Id
	 */
	public Integer insertOne(OrderItem orderItem) {
		String sql = "Insert into order_items(item_id, order_id, quantity, size) values (:itemId, :orderId, :quantity, :size) Returning id ";
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

	/**
	 * 注文商品情報を更新するリポジトリ.
	 * 
	 * @param orderItemId
	 * @param quantity
	 * @return 注文商品Id
	 */
	public Integer updateOrderItem(Integer orderItemId, Integer quantity) {
		String sql = "UPDATE order_items SET quantity = :quantity WHERE id = :orderItemId Returning id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", quantity).addValue("orderItemId",
				orderItemId);

		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}

	/**
	 * idで注文商品情報を取得する.
	 * 
	 * @param orderItemId
	 * @return 注文商品情報
	 */
	public OrderItem findOrderItembyId(Integer orderItemId) {
		String sql = "SELECT id, item_id, order_id, quantity, sise from order_items where id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);

		OrderItem orderItem = jdbcTemplate.queryForObject(sql, param, ORDER_ITEM_ROW_MAPPER);

		return orderItem;

	}

}
