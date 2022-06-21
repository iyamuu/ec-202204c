package com.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public OrderItem addToOrder (@RequestBody OrderItemForm orderItemForm){
		
		System.out.println(orderItemForm);
		
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		
		System.out.println(orderItemForm);
		System.out.println(orderItem);
		
		List<Integer> toppingIdList = orderItemForm.getToppingIdList();
		
		orderItem = orderItemService.addToOrder(orderItem, toppingIdList);
		
		return orderItem;
	}
}
