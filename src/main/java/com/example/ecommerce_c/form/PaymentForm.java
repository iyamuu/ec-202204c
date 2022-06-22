package com.example.ecommerce_c.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

/**
 * 支払い情報のformクラス
 * 
 * @author teranishidaina
 *
 */
public class PaymentForm {

	/** 支払い方法 0:現金 1:クレジットカード */
	@NotEmpty(message = "支払い方法を選択してください")
	private Integer pay;
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

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
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
		return "PaymentForm [pay=" + pay + ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear
				+ ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
	}

}
