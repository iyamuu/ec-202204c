package com.example.ecommerce_c.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

		// 現在時刻から３時間後を取得
		Date nowPlus3hour = new Date(new Date().getTime() + /* 3hour */(3 * 60 * 60 * 1000));
		// 配達時間を取得
		try {
			Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd-hh時")
					.parse(form.getDeliveryDate() + "-" + form.getDeliveryTime());
			order.setDeliveryTime(new Timestamp(deliveryTime.getTime()));
			// 配達時間が今から３時間以内
			if (nowPlus3hour.after(deliveryTime)) {
				result.rejectValue("deliveryTime", null, "配達日時は今から３時間以上後の時刻を選択してください");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		validation(form, result);

		if (result.hasErrors()) {
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
		if (paymentMethod == 0) {
			order.setStatus(1); // 未入金
//			メール送信
			mailService.sendMail(order);

			service.update(order);
			return "redirect:/complete";
		}

		else {
			OrderTransaction orderTransaction = new OrderTransaction();

			orderTransaction.setUser_id(order.getUserId());
			orderTransaction.setAmount(order.getCalcTotalPrice());
			orderTransaction.setOrder_number(order.getId());
			orderTransaction.setCard_number(form.getCardNumber());
			orderTransaction.setCard_exp_year(form.getCardExpYear());
			orderTransaction.setCard_exp_month(form.getCardExpMonth());
			orderTransaction.setCard_cvv(form.getCardCvv());

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

	/**
	 * フォームのバリデーションを行なう.
	 * 
	 * @param form
	 * @param result
	 */
	private void validation(ConfirmForm form, BindingResult result) {

		// 支払方法をクレジットカードに指定している場合のみバリデーション

		if (form.getPaymentMethod() == 1) { // クレジットカードのとき
			if (!form.getCardNumber().matches("^[0-9]{14}|^[0-9]{16}")) { // クレジットカードの番号が14桁または16桁ではないとき
				result.rejectValue("cardNumber", null, "カード番号の形式が正しくありません");
			}

			if (!form.getCardExpYear().matches("^[0-9]{4}")) { // クレジットカードの有効期限（年）は4桁
				result.rejectValue("cardExpYear", null, "クレジットカードの有効期限（年）は4桁で入力してください");
			}

			if (!form.getCardExpMonth().matches("^[0-9]{2}")) { // クレジットカードの有効期限（月）は2桁
				result.rejectValue("cardExpMonth", null, "クレジットカードの有効期限（年）は2桁で入力してください");
			}

			if (!form.getCardName().matches("^[a-zA-Z]{5,50}")) { // クレジットカードの名義は半角英字で50桁
				result.rejectValue("cardName", null, "クレジットカードの名義は半角英字の50桁以内で入力してください");
			}

			if (!form.getCardCvv().matches("^[0-9]{3}|^[0-9]{4}")) { // クレジットカードのセキュリティコードは３桁または４桁
				result.rejectValue("cardCvv", null, "クレジットカードのセキュリティコードは３桁または４桁で入力してください");
			}
		}
	}

}
