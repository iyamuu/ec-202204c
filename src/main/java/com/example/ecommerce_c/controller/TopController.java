package com.example.ecommerce_c.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.service.TopService;

/**
 * 商品一覧画面を出力するコントローラ.
 * 
 * @author hvthinh
 *
 */
@Controller
public class TopController {

	@Autowired
	private TopService service;

	/**
	 * 商品一覧画面を出力する.
	 * 
	 * @param model
	 * @return 商品一覧画面パース
	 */	
	@GetMapping("/top")
	public String index(Integer userId, Model model) {
//		ゲストユーザーかつ初アクセスなら
		if(userId == null) {
			Random rand = new Random();

//			重複しないゲスト用ユーザーIDを設定
			while(true) {
				System.out.println("make random user id.");
				int randomNum = (rand.nextInt(Integer.MAX_VALUE)+1) * -1;
				if(service.searchOrderByUserId(randomNum) == null) {
					userId = randomNum;
					break;
				}
			}
		}
		
		Integer orderId = service.searchOrderByUserId(userId);
		
//		初アクセスなら注文ドメインを新規作成
		if(orderId == null) {
			Order order = new Order();
			order.setStatus(0);
			order.getCalcTotalPrice();
			order.setUserId(userId);
			orderId = service.insertOrder(order);
		}
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", userId);
		return "item_list_toy";
	}

}
