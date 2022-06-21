package com.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.form.OrderItemForm;
import com.example.ecommerce_c.form.UpdateOrderItemForm;
import com.example.ecommerce_c.service.CartService;
import com.example.ecommerce_c.service.OrderItemService;

/**
 * 注文追加削除を行うコントローラ.
 * 
 * @author hvthinh
 *
 */
@RestController
public class OrderItemRestController {
	@Autowired
	private  OrderItemService orderItemService;
	@Autowired
	private CartService cartService;
	
	
	/**
	 * Orderを返す.
	 * 
	 * @param orderId オーダID
	 * @return　orderドメイン
	 */
	@PostMapping("/show")
	public Order showOrderItem(@RequestParam("orderId")Integer orderId) {
		Order order = cartService.getOrder(orderId);
		return order;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public OrderItem addToOrder (OrderItemForm orderItemForm){
		
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		
		List<Integer> toppingIdList = orderItemForm.getToppingIdList();
		
		if(toppingIdList == null) {
			toppingIdList = new ArrayList<>();
		}
		
		orderItem = orderItemService.addToOrder(orderItem, toppingIdList);
		
		return orderItem;
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteOrderItem(@RequestParam("orderItemId") Integer orderItemId) {
		orderItemService.deleteOrderItem(orderItemId);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public OrderItem updateToOrder(  UpdateOrderItemForm updateOrderItemForm) {
		OrderItem orderItem = orderItemService.updateToOrder(updateOrderItemForm.getOrderItemId(), updateOrderItemForm.getQuantity());
		return orderItem;
	}
}
