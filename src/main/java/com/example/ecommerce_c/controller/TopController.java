package com.example.ecommerce_c.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.form.ConfirmForm;
import com.example.ecommerce_c.security.LoginUser;
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
	@Autowired
	private HttpSession session;

	/**
	 * 商品一覧画面を出力する.
	 * 
	 * @param model
	 * @return 商品一覧画面パース
	 */
	@GetMapping("/")
	public String index(Integer userId, Model model, @AuthenticationPrincipal final LoginUser loginUser, ConfirmForm confirmForm) {
		System.out.println("loginUser: " + loginUser);
		session.setAttribute("loginUser", loginUser);
		System.out.println("  orderId: " + session.getAttribute("orderId"));
		if (loginUser == null && session.getAttribute("orderId") == null) {
//			ゲストユーザかつ初アクセスなら
//			重複しないゲスト用ユーザーIDを設定
			Random rand = new Random();
			while (true) {
				System.out.println("make random user id.");
				int randomNum = (rand.nextInt(Integer.MAX_VALUE) + 1) * -1;
				if (service.searchOrderByUserId(randomNum) == null) {
					userId = randomNum;
					break;
				}
			}
			
//			Orderを新規作成&DBに保存
			Order order = initOrder(userId);
			int orderId = service.insertOrder(order);
			
//			セッションスコープにorderIdを保存
			session.setAttribute("orderId", orderId);

			
		} else if (loginUser == null && session.getAttribute("orderId") != null) {
//			ゲストユーザかつ初アクセス以外なら、何もしない
		} else if (loginUser != null && session.getAttribute("orderId") != null) {
//			ゲストユーザからログインユーザに切り替えたら
//			ログインユーザのカートを取得
			Integer loginOrderId = service.searchOrderByUserId(loginUser.getUserId());
//			カートが空(=ログインユーザが新規登録だったら)
			if(loginOrderId == null) {
//				Orderを新規作成&DBに保存
				Order order = initOrder(loginUser.getUserId());
				loginOrderId = service.insertOrder(order);
			}
			
//			ログインユーザのカートにゲストユーザでカートに追加したアイテムを移動
			service.mergeOrder((Integer)session.getAttribute("orderId"), loginOrderId);
			
//			セッションスコープにorderIdを保存
			session.setAttribute("orderId", loginOrderId);
			
		} else if (loginUser != null && session.getAttribute("orderId") == null) {
//			ログイン済み or 直接ログインページにアクセスしてログイン
			Integer loginOrderId = service.searchOrderByUserId(loginUser.getUserId());
			if(loginOrderId == null) {
//				ログインユーザが新規登録だったら
//				Orderを新規作成&DBに保存
				Order order = initOrder(loginUser.getUserId());
				loginOrderId = service.insertOrder(order);
			}
//			セッションスコープにorderIdを保存
			session.setAttribute("orderId", loginOrderId);
//			セッションスコープにloginUserを保存
			
		}
		return "top_stepper";
	}
	
	private Order initOrder(int userId) {
		Order order = new Order();
		order.setStatus(0);
		order.getCalcTotalPrice();
		order.setUserId(userId);
		return order;
	}

}
