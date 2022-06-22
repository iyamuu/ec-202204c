package com.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.repository.ItemRepository;
import com.example.ecommerce_c.repository.OrderRepository;

@Service
public class TopService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * アイテムを取得する処理.
	 * 
	 * @param from スタート
	 * @param to   ゴール
	 * @param name 入力値
	 * @return アイテム
	 */
	public List<Item> getItemsByPage(int from, int to, String name) {
		if (name == null || name.isBlank()) {
			return itemRepository.findPages(from, to);
		}
		return itemRepository.findByName(from, to, name);
	}

	/**
	 * ユーザーIDから注文を検索する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文ID 初ログインorゲストならnull
	 */
	public Integer searchOrderByUserId(int userId) {
		Integer orderId = orderRepository.findOrderIdByUserId(userId);
		return orderId;
	}

	/**
	 * 注文を追加する.
	 * 
	 * @param order 注文
	 * @return 追加したOrderのID
	 */
	public int insertOrder(Order order) {
		int returningId = orderRepository.insert(order);
		return returningId;
	}
}
