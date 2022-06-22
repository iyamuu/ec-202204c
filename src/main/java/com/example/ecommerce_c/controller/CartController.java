package com.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@GetMapping("")
	public String showCart(Integer orderId, Model model) {
		
		model.addAttribute("orderId", orderId);
		return "cart_list";
	}
	
	@GetMapping("/test")
	public String test_showTop() {
		return "top";
	}
}
