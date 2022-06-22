package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService service;
	
	@GetMapping("")
	public String showCart(Integer orderId, Model model) {
		Order order = service.getOrder(orderId);
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", order.getUserId());		
		return "cart_list";
	}
	
	@GetMapping("/test")
	public String test_showTop() {
		return "top";
	}
}
