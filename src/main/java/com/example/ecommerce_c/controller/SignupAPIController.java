package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.service.SignupService;

/**
 * 新規登録時のメールアドレスの重複をチェックするAPI.
 * @author kouki
 *
 */
@RestController
@RequestMapping("")
public class SignupAPIController {
	
	@Autowired
	private SignupService signupService;
	
	/**
	 * メールアドレスの重複をチェックする.
	 * @param mail 新規ユーザのメールアドレス
	 * @return 重複していた場合はtrueを返す
	 */
	@RequestMapping("/duplicateCheckEmail")
	public Boolean isDuplicateEmail(String mail) {
		User existsUser = signupService.checkSameMailAddress(mail);
		if (existsUser != null) {
			return true;
		}
		return false;
	}

}
