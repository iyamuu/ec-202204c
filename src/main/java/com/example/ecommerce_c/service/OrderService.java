package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.repository.OrderRepository;

/**
 * 注文を操作するサービス.
 * 
 * @author ryuya.sasagawa
 *
 */
@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 注文を検索する
	 * 
	 * @param id ID
	 * @return 注文 存在しない場合はnullを返す
	 */
	public Order findById(int id) {
		Order order = repository.findById(id);
		return order;
	}

	/**
	 * 注文を登録する.
	 * 
	 * @param order 注文
	 * @return 登録した行のID
	 */
	public int insert(Order order) {
		int id = repository.insert(order);
		return id;
	}

	/**
	 * 注文内容を更新する.
	 * 
	 * @param order 注文
	 */
	public void update(Order order) {
		repository.update(order);
	}

}
