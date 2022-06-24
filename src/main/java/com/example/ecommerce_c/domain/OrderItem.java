package com.example.ecommerce_c.domain;

import java.util.ArrayList;
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
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;
	/** 商品情報. */
	private Item item;

	public OrderItem() {
		orderToppingList = new ArrayList<>();
	}

	/**
	 * 商品の合計金額を計算する.
	 * 
	 * @return トッピングを含めた合計金額
	 */
	public int getSubTotal() {
		int subTotal = 0;
		if(item == null) {
			return subTotal;
		}

		if (size == 'M') {
			subTotal += item.getPriceM();
			for (OrderTopping orderTopping : orderToppingList) {
				subTotal += orderTopping.getTopping().getPriceM();
			}
		} else {
			subTotal += item.getPriceL();
			for (OrderTopping orderTopping : orderToppingList) {
				subTotal += orderTopping.getTopping().getPriceL();
			}
		}

		subTotal = subTotal * quantity;
		return subTotal;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", orderToppingList=" + orderToppingList + ", item=" + item + "]";
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

}
