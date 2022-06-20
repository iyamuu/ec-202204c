package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Order;

/**
 * 注文を操作するリポジトリ.
 * 
 * @author ryuya.sasagawa
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

//	TODO: 注文商品も一緒に取得したい場合はResultSetExtractorを使う
	private static RowMapper<Order> ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;
	};

	/**
	 * 注文を検索する.
	 * 
	 * @param id ID
	 * @return 注文 存在しない場合はnullを返す
	 */
	public Order findById(int id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<Order> orderList = template.query(sql, param, ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);
	}

	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文
	 * @return 挿入した行のID
	 */
	public int insert(Order order) {
		String sql = 
			"INSERT INTO orders( id,  user_id,  status,  total_price,  order_date,  destination_name,  destination_email,  destination_zipcode,  destination_address,  destination_tel,  delivery_time,  payment_method) "
				+ "VALUES      (:id, :user_id, :status, :total_price, :order_date, :destination_name, :destination_email, :destination_zipcode, :destination_address, :destination_tel, :delivery_time, :payment_method)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		int returningId = template.queryForObject(sql, param, Integer.class);

		return returningId;
	}

	/**
	 * 注文内容を更新する.
	 * 
	 * @param order 注文
	 */
	public void update(Order order) {
		String sql = "UPDATE orders SET user_id=:user_id, status=:status, total_price=:total_price, order_date=:order_date, destination_name=:destination_name, destination_email=:destination_email, destination_zipcode=:destination_zipcode, destination_address=:destination_address, destination_tel=:destination_tel, delivery_time=:delivery_time, payment_method=:payment_method WHERE id=:id";

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		template.update(sql, param);
	}
}
