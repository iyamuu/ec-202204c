package com.example.ecommerce_c.domain;

public class OrderTransaction {
//	private Integer userId;
//	private Integer orderNumber;
//	private Integer amount;
//	private String cardNumber;
//	private Integer cardExpYear;
//	private Integer cardExpMonth;
//	private String cardName;
//	private Integer cardCvv;
	
	private Integer user_id;
	private Integer order_number;
	private Integer amount;
	private String card_number;
	private Integer card_exp_year;
	private Integer card_exp_month;
	private String card_name;
	private Integer card_cvv;
	
	
	@Override
	public String toString() {
		return "OrderTransaction [user_id=" + user_id + ", order_number=" + order_number + ", amount=" + amount
				+ ", card_number=" + card_number + ", card_exp_year=" + card_exp_year + ", card_exp_month="
				+ card_exp_month + ", card_name=" + card_name + ", cardCvv=" + card_cvv + "]";
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOrder_number() {
		return order_number;
	}
	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public Integer getCard_exp_year() {
		return card_exp_year;
	}
	public void setCard_exp_year(Integer card_exp_year) {
		this.card_exp_year = card_exp_year;
	}
	public Integer getCard_exp_month() {
		return card_exp_month;
	}
	public void setCard_exp_month(Integer card_exp_month) {
		this.card_exp_month = card_exp_month;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public Integer getCard_cvv() {
		return card_cvv;
	}
	public void setCard_cvv(Integer card_cvv) {
		this.card_cvv = card_cvv;
	}

//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public Integer getOrderNumber() {
//		return orderNumber;
//	}
//
//	public void setOrderNumber(Integer orderNumber) {
//		this.orderNumber = orderNumber;
//	}
//
//	public Integer getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}
//
//	public String getCardNumber() {
//		return cardNumber;
//	}
//
//	public void setCardNumber(String cardNumber) {
//		this.cardNumber = cardNumber;
//	}
//
//	public Integer getCardExpYear() {
//		return cardExpYear;
//	}
//
//	public void setCardExpYear(Integer cardExpYear) {
//		this.cardExpYear = cardExpYear;
//	}
//
//	public Integer getCardExpMonth() {
//		return cardExpMonth;
//	}
//
//	public void setCardExpMonth(Integer cardExpMonth) {
//		this.cardExpMonth = cardExpMonth;
//	}
//
//	public String getCardName() {
//		return cardName;
//	}
//
//	public void setCardName(String cardName) {
//		this.cardName = cardName;
//	}
//
//	public Integer getCardCvv() {
//		return cardCvv;
//	}
//
//	public void setCardCvv(Integer cardCvv) {
//		this.cardCvv = cardCvv;
//	}
//
//	@Override
//	public String toString() {
//		return "OrderTransaction [userId=" + userId + ", orderNumber=" + orderNumber + ", amount=" + amount
//				+ ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth
//				+ ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
//	}
	
	
}
