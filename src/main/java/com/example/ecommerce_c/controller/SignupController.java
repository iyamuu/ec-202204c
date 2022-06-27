package com.example.ecommerce_c.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce_c.domain.Addressee;
import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Payment;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.form.SignupForm;
import com.example.ecommerce_c.security.LoginUser;
import com.example.ecommerce_c.service.SignupService;

/**
 * 新規登録情報を扱うコントローラクラス.
 * 
 * @author hvthinh
 *
 */
@Controller
public class SignupController {

	@Autowired
	private SignupService signupService;

	/**
	 * 新規登録ページを返す.
	 * 
	 * @param form 新規登録フォーム
	 * @return 新規登録ページへのパス
	 */
	@GetMapping("/signup")
	public String getSignupPage(SignupForm form, Model model) {

		return "login/signup_stepper";
	}

	/**
	 * 新規登録を行なう. 登録が完了したら自動ログインする.
	 * 
	 * @param form   新規登録フォーム
	 * @param result バリデーション結果
	 * @return ログインページへのパス、エラーがあれば新規登録ページのパス
	 */
	@PostMapping("/signup")
	public String registerUser(@Validated SignupForm form, BindingResult result, Model model,
			HttpServletRequest request) {

		validation(form, result);

		if (result.hasErrors()) {
			return getSignupPage(form, model);
		}
		//ログイン状態なら一度ログアウトさせる
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken == false) {
			SecurityContextHolder.clearContext();
		}

		User user = new User();
		Addressee addressee = new Addressee();
		Payment payment = new Payment();
		GiftInformation giftInformation = new GiftInformation();

		BeanUtils.copyProperties(form.getUserForm(), user);
		BeanUtils.copyProperties(form.getAddresseeForm(), addressee);
		BeanUtils.copyProperties(form.getGiftInfoForm(), giftInformation);
		BeanUtils.copyProperties(form.getPaymentForm(), payment);

		user = signupService.registerUser(user, addressee, giftInformation, payment); // 登録処理、ここでidが付与される

		// 自動ログイン処理
		try {

			request.login(form.getUserForm().getEmail(), form.getUserForm().getPassword()); // パスワードは平文を渡す

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/";

	}
	
	
	@GetMapping("/line_signup")
	public String signupFromLine(@AuthenticationPrincipal LoginUser lineLoginUser, Model model, HttpServletRequest request) {
		
		if(lineLoginUser.getUserId() < 0) {  //IDが負の値ならアカウント登録情報はまだ LineIDをformに入れてサインアップページへ
			SignupForm form = new SignupForm();
			form.getUserForm().setLineId(lineLoginUser.getLineId());
			model.addAttribute("signupForm", form);
			return getSignupPage(form, model);
		}else {                              //IDがあるならそのままログイン
			
			return "redirect:/";
		}
	}

	/**
	 * フォームのバリデーションを行なう.
	 * 
	 * @param form
	 * @param result
	 */
	private void validation(SignupForm form, BindingResult result) {
		// emailの重複チェック、存在していればバリデーション結果にエラーを追加
		User existsUser = signupService.checkSameMailAddress(form.getUserForm().getEmail());
		if (existsUser != null) {
			result.rejectValue("userForm.email", null, "このメールアドレスは既に存在しています");
		}

		// パスワードと確認用パスワードの一致チェック
		String password = form.getUserForm().getPassword();
		String confirmPassword = form.getUserForm().getConfirmPassword();
		if (!password.equals(confirmPassword)) {
			result.rejectValue("userForm.confirmPassword", null, "パスワードが一致していません");
		}

		// 支払方法をクレジットカードに指定している場合のみバリデーション

		if (form.getPaymentForm().getPay() != null) {
			if (form.getPaymentForm().getPay() == 1) {
				if (!form.getPaymentForm().getCardNumber().matches("^[0-9]{14}|^[0-9]{16}")) { // クレジットカードの番号が14桁または16桁ではないとき
					result.rejectValue("paymentForm.cardNumber", null, "カード番号の形式が正しくありません");
				}

				if (!form.getPaymentForm().getCardExpYear().matches("^[0-9]{4}")) { // クレジットカードの有効期限（年）は4桁
					result.rejectValue("paymentForm.cardExpYear", null, "クレジットカードの有効期限（年）は4桁で入力してください");
				}

				if (!form.getPaymentForm().getCardExpMonth().matches("^[0-9]{2}")) { // クレジットカードの有効期限（月）は2桁
					result.rejectValue("paymentForm.cardExpMonth", null, "クレジットカードの有効期限（年）は2桁で入力してください");
				}

				if (!form.getPaymentForm().getCardName().matches("^[a-zA-Z]{5,50}")) { // クレジットカードの名義は半角英字で50桁
					result.rejectValue("paymentForm.cardName", null, "クレジットカードの名義は半角英字の50桁以内で入力してください");
				}

				if (!form.getPaymentForm().getCardCvv().matches("^[0-9]{3}|^[0-9]{4}")) { // クレジットカードのセキュリティコードは３桁または４桁
					result.rejectValue("paymentForm.cardCvv", null, "クレジットカードのセキュリティコードは３桁または４桁で入力してください");
				}
			}
		}
	}

}
