package com.example.ecommerce_c.domain;

import java.util.List;

/**
 * 注文商品情報を扱うドメイン.
 * 
 * @author hvthinh
 *
 */
public class OrderItem {
	/** 注文商品ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** カートID */
	private Integer orderId;
	/** 注文商品数 */
	private Integer quantity;
	/** 注文商品サイズ */
	private Character size;
	private List<Topping> orderToppingList;

	private Integer subTotal;
	
	

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", orderToppingList=" + orderToppingList + ", subTotal=" + subTotal + "]";
	}

	public Integer getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public List<Topping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Topping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
