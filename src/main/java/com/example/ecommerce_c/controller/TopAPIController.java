package com.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.service.TopService;

/**
 * 商品情報を取得するAPIコントローラ.
 * 
 * @author hvthinh
 *
 */
@RestController
@RequestMapping("")
public class TopAPIController {

	@Autowired
	TopService topService;

	/**
	 * 
	 * 商品情報をページによる取得する.
	 * 
	 * @param from
	 * @param to
	 * @param name
	 * @return ページによる商品情報リスト
	 */
	@RequestMapping("/getItemByPage")
	public List<Item> getItemsByPage(int from, int to, String name) {
		return topService.getItemsByPage(from, to, name);
	}
}
