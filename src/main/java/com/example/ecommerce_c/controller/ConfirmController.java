package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.form.ConfirmForm;
import com.example.ecommerce_c.service.ConfirmService;

/**
 * 注文確認画面を操作するコントローラー.
 * 
 * @author ryuya.sasagawa
 *
 */
@Controller
@RequestMapping("/confirm")
public class ConfirmController {
	@Autowired
	private ConfirmService service;

	@ModelAttribute
	public ConfirmForm setUpConfirmForm() {
		return new ConfirmForm();
	}

	/**
	 * 注文確認画面を表示する. ログインしていなければログイン画面に遷移する。
	 * 
	 * @param orderId 注文ID
	 * @param model   モデル
	 * @return 注文確認画面/ログイン画面
	 */
	@GetMapping("")
	public String showConfirm(int orderId, Model model) {
		Order order = service.searchOrder(orderId);

//		ログインしていなかったらログインページに遷移
//		if (order.getUserId() == -1) {
//			return "login/login";
//		}

//		注文内容
		model.addAttribute("order", service.getFullOrder(orderId));

		return "order_confirm";
	}
}
