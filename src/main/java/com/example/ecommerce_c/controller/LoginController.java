package com.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce_c.form.LoginForm;

/**
 * ログイン処理を行うCOntrollerクラス
 * 
 * @author daina.teranishi
 *
 */
@Controller
public class LoginController {
	
	/**
	 * ログインページを表示する.
	 * 
	 * @param form ログインフォーム
	 * @param model　リクエストスコープ
	 * @return ログインページのパス
	 */
	@GetMapping("/login")
	public String getLoginPage(LoginForm form, Model model) {
		
		return "login/login";
	}
	
}
