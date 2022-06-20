package com.example.ecommerce_c.form;

import javax.validation.constraints.Pattern;

/**
 * ログインフォーム.
 * 
 * @author daina.teranishi
 *
 */
public class LoginForm {

	/** メールアドレス */
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "メールアドレスは xxx@yyy.zzz 形式で入力してください")
	private String email;
	/** パスワード */
	@Pattern(regexp = "^[a-z\\d]{8,16}$", message = "パスワードは8文字以上１６文字以下で入力してください")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password + "]";
	}
}
