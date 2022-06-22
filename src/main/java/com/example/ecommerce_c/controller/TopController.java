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
