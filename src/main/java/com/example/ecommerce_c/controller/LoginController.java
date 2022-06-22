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
 * ログイン処理を行うCOntrollerクラス
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
	public String getLoginPage(LoginForm form, Model model) {

		return "login/login";
	}

	@PostMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result, Model model) {

		// formのバリデーションチェック
		if (result.hasErrors()) {
			return getLoginPage(form, model);
		}

		// ログイン成功したかのチェック
		User loginUser = loginService.login(form.getEmail(), form.getPassword());
		if (loginUser == null) {
			model.addAttribute("loginStatus", "メールアドレスかパスワードが異なります");
			return getLoginPage(form, model);
		}

		model.addAttribute("loginStatus", "ログインに成功しました");
//		HACK: あまりきれいな形じゃない
		return "redirect:/top?userId=" + loginUser.getId();

	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
}
