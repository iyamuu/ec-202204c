package com.example.ecommerce_c.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ユーザ情報をを表すドメインクラス.
 * 
 * @author daina.teranishi
 *
 */
public class User {

	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 電話番号 */
	private String telephone;

	private List<Addressee> addressees;
	private List<Payment> payments;
	private GitInformation giftInformation;
	
	public User() {
		
		addressees = new ArrayList<>();
		payments = new ArrayList<>();
		giftInformation = new GitInformation();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Addressee> getAddressees() {
		return addressees;
	}

	public void setAddressees(List<Addressee> addressees) {
		this.addressees = addressees;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public GitInformation getGiftInformation() {
		return giftInformation;
	}

	public void setGiftInformation(GitInformation giftInformation) {
		this.giftInformation = giftInformation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", telephone="
				+ telephone + ", addressees=" + addressees + ", payments=" + payments + ", giftInformation="
				+ giftInformation + "]";
	}

}
