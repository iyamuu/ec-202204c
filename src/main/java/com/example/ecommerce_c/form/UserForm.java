package com.example.ecommerce_c.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * ユーザ情報のFormクラス.
 * 
 * @author daina.teranishi
 *
 */
public class UserForm {
	/** 名前 */
	@NotEmpty(message = "名前を入力してください")
	private String name;
	
	/** メールアドレス */
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "メールアドレスは xxx@yyy.zzz 形式で入力してください")
	private String email;
	
	/** パスワード */
	@Pattern(regexp = "^[a-z\\d]{8,16}$", message = "パスワードは8文字以上１６文字以下で入力してください")
	private String password;
	
	/** 確認用パスワード */
	@NotEmpty(message = "上のパスワードと同じパスワードをここに入力してください")
	private String confirmPassword;
	
	/** 電話番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "電話番号を入力してください (ハイフンあり)")
	private String telephone;

	public UserForm() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "SignupForm [name=" + name + ", email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", telephone=" + telephone + "]";
	}


}
