package com.example.ecommerce_c.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("")
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
	@GetMapping("/confirm")
	public String showConfirm(int orderId, Model model) {
//		Order order = service.searchOrder(orderId);

//		ログインしていなかったらログインページに遷移
//		if (order.getUserId() == -1) {
//			return "login/login";
//		}

//		注文内容
		model.addAttribute("order", service.getFullOrder(orderId));

		return "order_confirm";
	}
	
	/**
	 * 注文を確定して注文完了画面を表示する.
	 * 
	 * @param form　宛先フォーム
	 * @return 注文完了画面
	 */
	@PostMapping("/purchase")
	public String finished(ConfirmForm form, Model model) {
		Order order = service.getFullOrder(form.getId());
//		NOTE: クレジット決済ならstatusを入金済(2)にする？
		order.setStatus(1); //未入金
		
//		フォームの内容をコピー
		BeanUtils.copyProperties(form, order);
		order.setOrderDate(new Date());
		try {
			Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd-hh時").parse(form.getDeliveryDate() + "-" + form.getDeliveryTime());
			order.setDeliveryTime(new Timestamp(deliveryTime.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		service.update(order);
		model.addAttribute("userId", order.getUserId());
		
		return "order_finished";
	}

}
