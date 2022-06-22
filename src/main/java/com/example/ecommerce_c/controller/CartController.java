package com.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * カート情報を操作するコントローラ.
 * 
 * @author hvthinh
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	/**
	 * 注文商品一覧画面を出力する.
	 * 
	 * @param orderId
	 * @param model
	 * @return 注文した商品一覧画面
	 */
	@GetMapping("")
	public String showCart(Integer orderId, Model model) {

		model.addAttribute("orderId", orderId);
		return "cart_list";
	}

}
