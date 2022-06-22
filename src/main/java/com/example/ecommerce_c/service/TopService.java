package com.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.repository.ItemRepository;

@Service
public class TopService {

	@Autowired
	ItemRepository itemRepository;

	/**
	 * アイテムを取得する処理.
	 * 
	 * @param from スタート
	 * @param to   ゴール
	 * @param name 入力値
	 * @return アイテム
	 */
	public List<Item> getItemsByPage(int from, int to, String name) {
		if (name == null || name.isBlank()) {
			return itemRepository.findPages(from, to);
		}
		return itemRepository.findByName(from, to, name);
	}
}
