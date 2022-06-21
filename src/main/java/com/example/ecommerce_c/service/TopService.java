package com.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.domain.Topping;
import com.example.ecommerce_c.repository.ItemRepository;
import com.example.ecommerce_c.repository.ToppingRepository;

@Service
public class TopService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ToppingRepository toppingRepository;
	/**
	 * アイテムを取得する処理
	 * @param from スタート
	 * @param to ゴール
	 * @param name 入力値
	 * @return アイテム
	 */
	public List<Item> getItemsByPage (int from, int to, String name){
		List<Topping> toppingList = toppingRepository.getTopping();
		List<Item> itemList = new ArrayList<>();
		
		if (name == null || name.isBlank()) {
			itemList = itemRepository.findPages(from, to);
		}else {
			itemList = itemRepository.findByName(from, to, name);
		}
		
		for (Item item : itemList) {
			item.setToppingList(toppingList);
		}
		System.out.println(toppingList);
		return itemList;
	}
 }
