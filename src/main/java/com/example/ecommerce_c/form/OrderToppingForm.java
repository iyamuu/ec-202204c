package com.example.ecommerce_c.form;

public class OrderToppingForm {
	private Integer id;
	private Integer toppingId;
	private Integer orderItemId;

	@Override
	public String toString() {
		return "OrderToppingForm [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

}
