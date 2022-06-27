package com.example.ecommerce_c.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * オーダー情報を出力するコントローラ.
 * 
 * @author hvthinh
 *
 */
public class ConfirmForm {
	/** ID */

	private Integer orderId;

	/** 宛先氏名 */
	@NotEmpty(message = "宛先氏名を入力してください")
	private String destinationName;
	/** 宛先Eメール */
	@NotEmpty(message = "メールアドレスを入力してください")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号を入力してください (ハイフンあり）")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotEmpty(message = "住所を入力してください (建物名まで)")
	private String destinationAddress;
	/** 宛先TEL */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "電話番号を入力してください (ハイフンあり)")
	private String destinationTel;
	/** 配達日時 */
	@NotEmpty(message = "配達日時を選択してください")
	private String deliveryDate;
	/** 配達時間 */
	@NotEmpty(message = "配達時間を選択してください")
	private String deliveryTime;

	/** 支払方法 */
	private Integer paymentMethod;
	/** クレジットカード番号 payが１なら存在する */
	private String cardNumber;
	/** クレジットカードの期限(年) payが１なら存在する */
	private String cardExpYear;
	/** クレジットカードの期限(月) payが１なら存在する */
	private String cardExpMonth;
	/** クレジットカードの名義 payが１なら存在する */
	private String cardName;
	/** クレジットカードのセキュリティコード payが１なら存在する */
	private String cardCvv;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public String getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	@Override
	public String toString() {
		return "ConfirmForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", cardNumber=" + cardNumber
				+ ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName
				+ ", cardCvv=" + cardCvv + ", getOrderId()=" + getOrderId() + ", getDestinationName()="
				+ getDestinationName() + ", getDestinationEmail()=" + getDestinationEmail()
				+ ", getDestinationZipcode()=" + getDestinationZipcode() + ", getDestinationAddress()="
				+ getDestinationAddress() + ", getDestinationTel()=" + getDestinationTel() + ", getDeliveryDate()="
				+ getDeliveryDate() + ", getDeliveryTime()=" + getDeliveryTime() + ", getPaymentMethod()="
				+ getPaymentMethod() + ", getCardNumber()=" + getCardNumber() + ", getCardExpYear()=" + getCardExpYear()
				+ ", getCardExpMonth()=" + getCardExpMonth() + ", getCardName()=" + getCardName() + ", getCardCvv()="
				+ getCardCvv() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


}