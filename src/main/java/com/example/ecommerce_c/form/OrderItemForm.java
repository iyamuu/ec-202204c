package com.example.ecommerce_c.form;

import java.util.List;

/**
 * 注文商品の情報を扱うフォーム.
 * 
 * @author hvthinh
 *
 */
public class OrderItemForm {
	/** 注文ID */
	private Integer orderId;
	/** 商品ID */
	private Integer itemId;
	/** 商品のサイズ */
	private Character size;
	/** 商品数 */
	private Integer quantity;
	/** トッピングIdリスト */
	private List<Integer> toppingIdList;

	@Override
	public String toString() {
		return "OrderItemForm [orderId=" + orderId + ", itemId=" + itemId + ", size=" + size + ", quantity=" + quantity
				+ ", toppingIdList=" + toppingIdList + "]";
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

}
