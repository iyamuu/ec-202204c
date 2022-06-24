package com.example.ecommerce_c.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	@NotNull(message = "宛先氏名を入力してください")
	private String destinationName;
	/** 宛先Eメール */
	@NotNull(message = "メールアドレスを入力してください")
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
	@NotNull(message = "配達日時を選択してください")
	private String deliveryDate;
	/** 配達時間 */
	@NotNull(message = "配達時間を選択してください")
	private String deliveryTime;

	/** 支払方法 */
	@NotNull(message = "支払方法を選択してください")
	private Integer paymentMethod;

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

	@Override
	public String toString() {
		return "ConfirmForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}