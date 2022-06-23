package com.example.ecommerce_c.form;

import javax.validation.Valid;

/**
 * サインアップ用のformクラス
 * 
 * @author teranishidaina
 *
 */
public class SignupForm {

	/** アカウント情報 */
	@Valid
	private UserForm userForm;
	/** 宛先情報 */
	@Valid
	private AddresseeForm addresseeForm;
	/** 絞り込み情報 */
	@Valid
	private GiftInfoForm giftInfoForm;
	/** 支払い情報 */
	@Valid
	private PaymentForm paymentForm;

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public AddresseeForm getAddresseeForm() {
		return addresseeForm;
	}

	public void setAddresseeForm(AddresseeForm addresseeForm) {
		this.addresseeForm = addresseeForm;
	}

	public GiftInfoForm getGiftInfoForm() {
		return giftInfoForm;
	}

	public void setGiftInfoForm(GiftInfoForm giftInfoForm) {
		this.giftInfoForm = giftInfoForm;
	}

	public PaymentForm getPaymentForm() {
		return paymentForm;
	}

	public void setPaymentForm(PaymentForm paymentForm) {
		this.paymentForm = paymentForm;
	}

	@Override
	public String toString() {
		return "SignupForm [userForm=" + userForm + ", addresseeForm=" + addresseeForm + ", giftInfoForm="
				+ giftInfoForm + ", paymentForm=" + paymentForm + "]";
	}
}
