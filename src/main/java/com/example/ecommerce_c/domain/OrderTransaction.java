package com.example.ecommerce_c.domain;

public class OrderTransaction {
	private Integer userId;
	private Integer orderNumber;
	private Integer amount;
	private Integer cardNumber;
	private Integer cardExpYear;
	private Integer cardExpMonth;
	private String cardName;
	private Integer cardCvc;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public Integer getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardCvc() {
		return cardCvc;
	}

	public void setCardCvc(Integer cardCvc) {
		this.cardCvc = cardCvc;
	}

	@Override
	public String toString() {
		return "OrderTransaction [userId=" + userId + ", orderNumber=" + orderNumber + ", amount=" + amount
				+ ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth
				+ ", cardName=" + cardName + ", cardCvc=" + cardCvc + "]";
	}
	
	
}
