package com.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.OrderRepository;
import com.example.ecommerce_c.repository.UserRepository;

@Service
public class ConfirmService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文を検索する
	 * 
	 * @param id ID
	 * @return 注文 存在しない場合はnullを返す
	 */
	public Order searchOrder(int id) {
		Order order = orderRepository.findById(id);
		return order;
	}

	/**
	 * 注文を登録する.
	 * 
	 * @param order 注文
	 * @return 登録した行のID
	 */
	public int insert(Order order) {
		int id = orderRepository.insert(order);
		return id;
	}

	/**
	 * 注文内容を更新する.
	 * 
	 * @param order 注文
	 */
	public void update(Order order) {
		orderRepository.update(order);
	}

	
	/**
	 * ユーザーを検索する.
	 * 
	 * @param id userID
	 * @return ユーザー
	 */
	public User searchUser(int id) {
		User user = userRepository.findById(id);
		return user;
	}
	
	/**
	 * 注文を取得する.
	 * 
	 * @param orderId orderID
	 * @return 注文
	 */
	public Order getFullOrder(int orderId) {
		Order order = orderRepository.findFullOrderById(orderId);
		return order;
	}
}
