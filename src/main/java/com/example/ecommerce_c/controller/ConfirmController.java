package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.service.OrderService;

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
	private OrderService orderService;
	
//	@Autowired
//	private UserService userService;
	
	/**
	 * 注文確認画面を表示する.
	 * ログインしていなかったらログインページに遷移する。
	 * 
	 * @param orderId ID
	 * @return 注文確認画面
	 */
	@RequestMapping("")
	public String showConfirm(int orderId) {
//		FIXME: ユーザードメインやユーザーサービスが実装されないとログインしているかの判定ができない
		Order order = orderService.findById(orderId);
		
//		User user = userService.findById(order.getUserId());
//		
//		if(user == null) {
//			return "redirect:/login";
//		}
		
		return "order_confirm";
	}
	
//	リポジトリとサービスの動作確認
	/**
	 * サービスの動作確認をする
	 * 
	 * @return 注文確認画面
	 */
//	@RequestMapping("/test")
	public String test() {
//		WORNING: テスト用。insertやupdateが行われるため注意！テストするときはRequestMappingのコメントアウトを外してください。
		System.out.println("orderService.findById (expected 'null'): " + orderService.findById(1));
		Order order = new Order();
		order.setUserId(12345);
		order.setStatus(0);
		order.setTotalPrice(0);
		
		System.out.println(order);

		System.out.println("orderService.insert (expected '1')" + orderService.insert(order));
		Order order2 = orderService.findById(1);
		System.out.println("orderService.findById (expected 'order[id=1, ...]'): " + order2);
		order2.setTotalPrice(10000);
		orderService.update(order2);
		System.out.println("update complate.");
		System.out.println("orderService.findById (expected 'order[id=1, ... , totalPrice=10000, ...]'): " + orderService.findById(1));
		return "order_confirm";
	}
}
