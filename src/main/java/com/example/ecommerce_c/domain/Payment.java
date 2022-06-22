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
	/** クレジットカードの期限 payが１なら存在する */
	private String expire;
	/** クレジットカードのセキュリティコード payが１なら存在する */
	private String code;

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

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", userId=" + userId + ", pay=" + pay + ", cardNumber=" + cardNumber + ", expire="
				+ expire + ", code=" + code + "]";
	}

}
