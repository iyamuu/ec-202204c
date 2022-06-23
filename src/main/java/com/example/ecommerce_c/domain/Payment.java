package com.example.ecommerce_c.domain;

/**
 * 支払情報を表すドメインクラス.
 * 
 * @author daina.teranishi
 *
 */
public class Payment {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 支払方法 0:代引き 1:クレジットカード */
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
		return "Payment [id=" + id + ", userId=" + userId + ", pay=" + pay + ", cardNumber=" + cardNumber
				+ ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName
				+ ", cardCvv=" + cardCvv + "]";
	}

}
