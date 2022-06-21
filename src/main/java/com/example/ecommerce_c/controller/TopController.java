package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.service.OrderService;

@Controller
@RequestMapping("/")
public class TopController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping("")
	public String index(Model model) {
		Order order = new Order();
		order.setStatus(0);
		order.setTotalPrice(order.getCalcTotalPrice());
		order.setUserId(-1);
		Integer orderId = orderService.insert(order);
		model.addAttribute("orderId", orderId);
		return "item_list_toy";
	}
	
}
