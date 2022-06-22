package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.service.OrderService;

/**
 * 商品一覧画面を出力するコントローラ.
 * 
 * @author hvthinh
 *
 */
@Controller
public class TopController {

	@Autowired
	OrderService orderService;

	/**
	 * 商品一覧画面を出力する.
	 * 
	 * @param model
	 * @return 商品一覧画面パース
	 */
	@GetMapping("/top")
	public String index(Integer userId, Model model) {
		if(userId == null) {
			userId = -1;
		}
		
		Order order = new Order();
		order.setStatus(0);
		order.getCalcTotalPrice();
		order.setUserId(userId);
		Integer orderId = orderService.insert(order);
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", userId);
		return "item_list_toy";
	}

}
