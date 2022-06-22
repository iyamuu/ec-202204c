package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.form.LoginForm;
import com.example.ecommerce_c.service.LoginService;

/**
 * ログイン処理を行うControllerクラス
 * 
 * @author daina.teranishi
 *
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * ログインページを表示する.
	 * 
	 * @param form  ログインフォーム
	 * @param model リクエストスコープ
	 * @return ログインページのパス
	 */
	@GetMapping("/login")
	public String getLoginPage(LoginForm form, Integer userId, Model model) {
		model.addAttribute("userId", userId);
		return "login/login";
	}

	@PostMapping("/login")
	public String login(@Validated LoginForm form, Integer userId, BindingResult result, Model model) {

		// formのバリデーションチェック
		if (result.hasErrors()) {
			return getLoginPage(form, userId, model);
		}

		// ログイン成功したかのチェック
		User loginUser = loginService.login(form.getEmail(), form.getPassword());
		if (loginUser == null) {
			model.addAttribute("loginStatus", "メールアドレスかパスワードが異なります");
			return getLoginPage(form, userId, model);
		}
		
//		ゲストユーザのときにカートに追加した商品を、ログインしたユーザのカートに移動
		loginService.mergeOrder(loginUser.getId(), userId);

		model.addAttribute("loginStatus", "ログインに成功しました");
//		HACK: あまりきれいな形じゃない
		return "redirect:/top?userId=" + loginUser.getId();

	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
}
