package com.example.ecommerce_c.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Item;

@Repository
public class ItemRepository {
	
	/**
	 * fromからtoまでのアイテムを取得
	 * @param from スタート
	 * @param to ゴール
	 * @return アイテム
	 */
	public List<Item> findPages(int from, int to) {
		List<Item> pages = new ArrayList<>();
		//fromからtoまでのアイテムを取得
		return pages;
	}
	
	/**
	 * あいまい検索でアイテムを取得
	 * @param name
	 * @return
	 */
	public List<Item> findByName(String name) {
		List<Item> pages = new ArrayList<>();
		
		return pages;
	}

}
