package com.example.ecommerce_c.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.OrderTopping;
import com.example.ecommerce_c.domain.Topping;
import com.example.ecommerce_c.domain.User;

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
	
	private static ResultSetExtractor<List<Order>> FULL_ORDER_MAPPER = (rs) ->{
		List<Order> orderList = new ArrayList<>();
		Order order = null;
		OrderItem orderItem = null;
		OrderTopping orderTopping = null;
		
		int beforeOrderId=-1;
		int beforeOrderItemId=-1;
		int beforeOrderToppingId=-1;
		
		while(rs.next()) {
			int orderId = rs.getInt("id");
			int orderItemId = rs.getInt("orderitem_id");
			int orderToppingId = rs.getInt("ordertopping_id");
			
//			新しい注文だったら
			if(beforeOrderId != orderId) {
//				注文ドメインを作成
				order = new Order();
				order.setId(orderId);
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
				order.setOrderItemList(new ArrayList<OrderItem>());
				
//				ユーザードメインを作成
				User user = new User();
				user.setId(order.getUserId());
				user.setName("user_name");
				user.setEmail(rs.getString("user_email"));
				user.setPassword(rs.getString("user_password"));
				user.setTelephone(rs.getString("user_telephone"));
				
//				注文ドメインにユーザードメインを格納
				order.setUser(user);
				
//				注文リストに追加
				orderList.add(order);
				
//				今処理している注文を更新
				beforeOrderId = orderId;
//				注文商品を初期化
				beforeOrderItemId = -1;
			}
			
//			注文商品が1つもない場合を除外
			if(orderItemId != 0) {
//				新しい注文商品だったら
				if(beforeOrderItemId != orderItemId) {
//					注文商品ドメインを作成
					orderItem = new OrderItem();
					orderItem.setId(orderItemId);
					orderItem.setItemId(rs.getInt("orderitem_item_id"));
					orderItem.setOrderId(orderId);
					orderItem.setQuantity(rs.getInt("orderitem_quantity"));
					orderItem.setSize(rs.getString("orderitem_size").charAt(0));
					orderItem.setOrderToppingList(new ArrayList<OrderTopping>());
					
//					商品ドメインを作成
					Item item = new Item();
					item.setId(orderItem.getItemId());
					item.setName(rs.getString("item_name"));
					item.setDescription(rs.getString("item_description"));
					item.setPriceM(rs.getInt("item_price_m"));
					item.setPriceL(rs.getInt("item_price_l"));
					item.setImagePath(rs.getString("item_image_path"));
					item.setDeleted(rs.getBoolean("item_deleted"));
					
//					商品ドメインを注文商品ドメインに格納
					orderItem.setItem(item);
					
					order.getOrderItemList().add(orderItem);
					
//					今処理している商品を更新
					beforeOrderItemId = orderItemId;
//					トッピングを初期化
					beforeOrderToppingId = -1;
				}
			}
//			トッピングが1つもない場合を除外
			if(orderToppingId != 0) {
				if(beforeOrderToppingId != orderToppingId) {
//					注文トッピングドメインを作成
					orderTopping = new OrderTopping();
					orderTopping.setId(orderToppingId);
					orderTopping.setToppingId(rs.getInt("ordertopping_topping_id"));
					orderTopping.setOrderItemId(orderItemId);
					
//					トッピングドメインを作成
					Topping topping = new Topping();
					topping.setId(orderTopping.getToppingId());
					topping.setName(rs.getString("topping_name"));
					topping.setPriceM(rs.getInt("topping_price_m"));
					topping.setPriceL(rs.getInt("topping_price_l"));
					
//					トッピングドメインを注文トッピングドメインに格納
					orderTopping.setTopping(topping);
					
					orderItem.getOrderToppingList().add(orderTopping);
					
//					今処理しているトッピングを更新
					beforeOrderToppingId = orderToppingId;
				}
			}
		}
		return orderList;
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
			"INSERT INTO orders( user_id,  status,  total_price,  order_date,  destination_name,  destination_email,  destination_zipcode,  destination_address,  destination_tel,  delivery_time,  payment_method) "
				+ "VALUES      (:userId, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode, :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod) "
				+ "RETURNING id";

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
		String sql = "UPDATE orders SET user_id=:userId, status=:status, total_price=:totalPrice, order_date=:orderDate, destination_name=:destinationName, destination_email=:destinationEmail, destination_zipcode=:destinationZipcode, destination_address=:destinationAddress, destination_tel=:destinationTel, delivery_time=:deliveryTime, payment_method=:paymentMethod WHERE id=:id";

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		template.update(sql, param);
	}
	
	/**
	 * Order情報を取得するローマッパー.
	 * 
	 * @param id
	 * @return
	 */
	public Order findFullOrderById(int id) {
		String sql = "SELECT "
				+ "o.id as id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method, "
				+ "u.name as user_name, u.email as user_email, u.password as user_password, u.zipcode as user_zipcode, u.address as user_address, u.telephone as user_telephone, "
				+ "oi.id as orderitem_id, oi.item_id as orderitem_item_id, oi.quantity as orderitem_quantity, oi.size as orderitem_size, "
				+ "i.name as item_name, i.description as item_description, i.price_m as item_price_m, i.price_l as item_price_l, i.image_path as item_image_path, i.deleted as item_deleted, "
				+ "ot.id as ordertopping_id, ot.topping_id as ordertopping_topping_id, "
				+ "t.name as topping_name, t.price_m as topping_price_m, t.price_l as topping_price_l "
				+ "FROM orders as o "
				+ "LEFT OUTER JOIN users          as  u ON  u.id = o.user_id "
				+ "LEFT OUTER JOIN order_items    as oi ON  o.id = oi.order_id "
				+ "LEFT OUTER JOIN items          as  i ON  i.id = oi.item_id "
				+ "LEFT OUTER JOIN order_toppings as ot ON oi.id = ot.order_item_id "
				+ "LEFT OUTER JOIN toppings       as  t ON  t.id = ot.topping_id "
				+ "WHERE o.id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<Order> orderList = template.query(sql, param, FULL_ORDER_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);

	}
	
	/**
	 * ユーザーIDから注文IDを検索する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文ID 該当する注文が無ければnullを返す
	 */
	public Integer findOrderIdByUserId(int userId) {
		String sql = "SELECT * FROM orders WHERE user_id = :userId AND status = 0 ORDER BY id DESC";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);

		List<Order> orderList = template.query(sql, param, ROW_MAPPER);
		if(orderList.size() == 0) {
			return null;
		}
		return orderList.get(0).getId();
	}
}
