package com.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.Topping;
import com.example.ecommerce_c.repository.GifInformationRepository;
import com.example.ecommerce_c.repository.ItemRepository;
import com.example.ecommerce_c.repository.OrderItemRepository;
import com.example.ecommerce_c.repository.OrderRepository;
import com.example.ecommerce_c.repository.ToppingRepository;

@Service
public class TopService {

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ToppingRepository toppingRepository;
	@Autowired
	GifInformationRepository giftInfoRepository;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * 初期の絞り込みを行う.
	 * 
	 * @param from 何件目から
	 * @param to   何件目まで
	 * @return アイテム
	 */
	public List<Item> getItemsByPage(int from, int to, GiftInformation giftInformation) {
		List<Topping> toppingList = toppingRepository.getTopping();
		List<Item> itemList = new ArrayList<>();
		itemList = itemRepository.findPages(from, to, giftInformation);

		for (Item item : itemList) {
			item.setToppingList(toppingList);
		}
		return itemList;
	}

	public List<Item> searchByName(int from, int to, String name) {
		List<Topping> toppingList = toppingRepository.getTopping();
		List<Item> itemList = new ArrayList<>();
		itemList = itemRepository.findByName(from, to, name);

		for (Item item : itemList) {
			item.setToppingList(toppingList);
		}

		return itemList;
	}

	/**
	 * ユーザーIDから注文を検索する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文ID 初ログインorゲストならnull
	 */
	public Integer searchOrderByUserId(int userId) {
		Integer orderId = orderRepository.findOrderIdByUserId(userId);
		return orderId;
	}

	/**
	 * 注文を追加する.
	 * 
	 * @param order 注文
	 * @return 追加したOrderのID
	 */
	public int insertOrder(Order order) {
		int returningId = orderRepository.insert(order);
		return returningId;
	}

	/**
	 * ゲストユーザでカートに追加した商品を、ログインユーザのカートに移動させる.
	 * 
	 * @param guestOrderId ゲストユーザのorderID
	 * @param loginOrderId ログインユーザのorderID
	 */
	public void mergeOrder(int guestOrderId, int loginOrderId) {
//		ゲストユーザの注文商品のユーザIDを、登録済みユーザのユーザIDに変更
		Order order = orderRepository.findFullOrderById(guestOrderId);
		for (OrderItem orderItem : order.getOrderItemList()) {
			orderItem.setOrderId(loginOrderId);
			orderItemRepository.update(orderItem);
		}
	}
	
	
	/**
	 * ユーザIDから絞り込み情報を取得する.
	 * 
	 * @param userId　ユーザID
	 * @return　絞り込み情報
	 */
	public GiftInformation getGiftInfoByUserId(Integer userId) {
		
		return giftInfoRepository.findOneByUserId(userId);
	}
	
	public Order getFullOrder(int orderId) {
		return orderRepository.findFullOrderById(orderId);
	}

}
