package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.repository.OrderRepository;

/**
 * カートの注文商品情報を取得するサービス.
 * 
 * @author hvthinh
 *
 */
@Service
public class CartService {
	@Autowired
	private OrderRepository repository;

	/**
	 * Order情報を取得する.
	 * 
	 * @param orderId
	 * @return orderドメイン
	 */
	public Order getOrder(int orderId) {
		Order order = repository.findFullOrderById(orderId);
		order.getCalcTotalPrice();
		return order;
	}
}
