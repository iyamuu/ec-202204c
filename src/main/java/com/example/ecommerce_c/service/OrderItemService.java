package com.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.OrderTopping;
import com.example.ecommerce_c.domain.Topping;
import com.example.ecommerce_c.repository.OrderItemRepository;
import com.example.ecommerce_c.repository.OrderToppingRepository;

/**
 * OrderItemとOrderToppingを追加するサービス.
 * 
 * @author hvthinh
 *
 */
@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	public OrderItem addToOrder(OrderItem orderItem, List<Integer> toppingIdList) {
		Integer orderItemId = orderItemRepository.insertOne(orderItem);
		orderItem.setId(orderItemId);

		List<OrderTopping> orderToppingList = new ArrayList<>();
		for (Integer toppingId : toppingIdList) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(toppingId);
			;
			orderTopping.setOrderItemId(orderItemId);

			Integer orderToppingId = orderToppingRepository.insertOrderTopping(orderTopping);
			orderTopping.setId(orderToppingId);

			orderToppingList.add(orderTopping);
		}

		orderItem.setOrderToppingList(orderToppingList);
		
		return orderItem;
	}
}
