package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.User;
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
	@RequestMapping("")
	public String showConfirm(int orderId, Model model) {
		Order order = service.searchOrder(orderId);
		User user = service.searchUser(order.getUserId());

		if (user == null) {
			return "login/login";
		}

//		たぶん必要
		model.addAttribute("orderId", orderId);
//		注文商品一覧
		model.addAttribute("orderItemList", service.stab_searchOrderItemByOrderId(orderId));

		return "order_confirm";
	}
	
	@RequestMapping("/test")
	public String stab_showCartList() {
		return "cart_list";
	}
}
