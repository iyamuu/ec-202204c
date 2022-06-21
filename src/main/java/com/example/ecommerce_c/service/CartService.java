package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.repository.OrderRepository;

@Service
public class CartService {
	@Autowired
	private OrderRepository repository;
	
	public Order getOrder(int orderId) {
		return repository.findFullOrderById(orderId);
	}
}
