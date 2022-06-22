package com.example.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.form.UserForm;
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
	public String getSignupPage(UserForm form, Model model) {
		return "login/signup_stepper";
	}

	/**
	 * 新規登録を行なう.
	 * 
	 * @param form   新規登録フォーム
	 * @param result バリデーション結果
	 * @return ログインページへのパス、エラーがあれば新規登録ページのパス
	 */
	@PostMapping("/signup")
	public String registerUser(@Validated UserForm form, Integer userId, BindingResult result, Model model) {

		// emailの重複チェック、存在していればバリデーション結果にエラーを追加
		User existsUser = signupService.checkSameMailAddress(form.getEmail());
		if (existsUser != null) {
			result.rejectValue("email", null, "このメールアドレスは既に存在しています");
		}

		// パスワードと確認用パスワードの一致チェック
		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();
		if (!password.equals(confirmPassword)) {
			result.rejectValue("confirmPassword", null, "パスワードが一致していません");
		}

		if (result.hasErrors()) {
			return getSignupPage(form, model);
		}

		User newUser = new User();
		BeanUtils.copyProperties(form, newUser);
		newUser = signupService.registerUser(newUser); // 登録処理、ここでidが付与される

		model.addAttribute("userId", userId);
		return "redirect:/login";

	}

}
