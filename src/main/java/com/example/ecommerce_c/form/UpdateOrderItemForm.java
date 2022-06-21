package com.example.ecommerce_c.form;

/**
 * 注文商品情報を更新するフォーム.
 * 
 * @author hvthinh
 *
 */
public class UpdateOrderItemForm {
	/** 注文商品Id */
	private Integer orderItemId;
	/** 注文商品個数 */
	private Integer quantity;

	@Override
	public String toString() {
		return "UpdateOrderItemForm [orderItemId=" + orderItemId + ", quantity=" + quantity + "]";
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
