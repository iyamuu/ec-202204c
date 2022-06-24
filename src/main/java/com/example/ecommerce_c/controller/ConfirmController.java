package com.example.ecommerce_c.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderTransaction;
import com.example.ecommerce_c.domain.OrderTransactionStatus;
import com.example.ecommerce_c.domain.Payment;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.form.ConfirmForm;
import com.example.ecommerce_c.mail.MailService;
import com.example.ecommerce_c.security.LoginUser;
import com.example.ecommerce_c.service.ConfirmService;
import com.example.ecommerce_c.service.OrderTransactionService;
import com.example.ecommerce_c.service.PaymentService;

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
	@Autowired
	private MailService mailService;
	@Autowired
	private OrderTransactionService orderTransactionService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private TopController topController;

//	@Autowired
//	private 

	@ModelAttribute
	ConfirmForm setUpConfirmForm() {
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
	public String showConfirm(int orderId, Model model, ConfirmForm confirmForm) {
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
	 * @param form 宛先フォーム
	 * @return 注文完了画面
	 */
	@PostMapping("/purchase")
	public String finished(@Validated ConfirmForm form, BindingResult result, Model model,
			@AuthenticationPrincipal final LoginUser loginUser) {
		Order order = service.getFullOrder(form.getOrderId());

		if (result.hasErrors()) {
			// どこのパースを返せばいいのか.

			System.out.println(form);
			model.addAttribute("confirmForm", form);
			return topController.index(order.getUserId(), model, loginUser, form);
		}

		model.addAttribute("userId", order.getUserId());

//		フォームの内容をコピー
		BeanUtils.copyProperties(form, order);
		order.setOrderDate(new Date());
		// 支払方法情報を取得する
		order.setPaymentMethod(form.getPaymentMethod());
		try {
			Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd-hh時")
					.parse(form.getDeliveryDate() + "-" + form.getDeliveryTime());
			order.setDeliveryTime(new Timestamp(deliveryTime.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Integer paymentMethod = order.getPaymentMethod();
		if (paymentMethod == 1) {
			order.setStatus(1); // 未入金
//			メール送信
			mailService.sendMail(order);

			service.update(order);
			return "redirect:/complete";
		}

		else {
			Payment payment = paymentService.findOneByUserId(order.getUserId());
			OrderTransaction orderTransaction = new OrderTransaction();

			orderTransaction.setUser_id(order.getUserId());
			orderTransaction.setAmount(order.getCalcTotalPrice());
			orderTransaction.setOrder_number(order.getId());
			orderTransaction.setCard_number(payment.getCardNumber());
			orderTransaction.setCard_exp_year(payment.getCardExpYear());
			orderTransaction.setCard_exp_month(payment.getCardExpMonth());
//			orderTransaction.setCard_cvv(payment.getCardCvv());
			//test 決済失敗
			orderTransaction.setCard_cvv("111");

			OrderTransactionStatus orderTransactionStatus = orderTransactionService.transacting(orderTransaction);

			System.out.println(orderTransactionStatus);
			if (orderTransactionStatus.getStatus().equals("error")) { // 決済失敗した場合
				result.rejectValue("paymentMethod", null, orderTransactionStatus.getMessage());
				return topController.index(order.getUserId(), model, loginUser, form);
			} else { // 決済成功
				order.setStatus(2);
				service.update(order);
				
//				注文内容確認&入金確認メール
				mailService.sendMail(order);

				return "redirect:/complete";
			}
		}

	}
	
	@GetMapping("/complete")
	public String purchase() {
		return "order_finished";
	}

}
