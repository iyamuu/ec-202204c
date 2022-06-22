package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.service.CartService;

/**
 * カート情報を操作するコントローラ.
 * 
 * @author hvthinh
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService service;

	/**
	 * 注文商品一覧画面を出力する.
	 * 
	 * @param orderId
	 * @param model
	 * @return 注文した商品一覧画面
	 */
	@GetMapping("")
	public String showCart(Integer orderId, Model model) {
		
		Order order = service.getOrder(orderId);
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", order.getUserId());		
		return "cart_list";
	}

}
