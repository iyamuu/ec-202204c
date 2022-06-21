package com.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.form.OrderItemForm;
import com.example.ecommerce_c.service.OrderItemService;

/**
 * 注文追加削除を行うコントローラ.
 * 
 * @author hvthinh
 *
 */
@RestController
public class OrderItemRestController {
	@Autowired
	private  OrderItemService orderItemService;
	
	@PostMapping("/add")
	public OrderItem addToOrder (OrderItemForm orderItemForm){
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		
		List<Integer> toppingIdList = orderItemForm.getToppingIdList();
		
		orderItem = orderItemService.addToOrder(orderItem, toppingIdList);
		
		return orderItem;
	}
}
