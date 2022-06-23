package com.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.security.LoginUser;
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
	public List<Item> getItemsByPage(Integer from, Integer to, String name, @AuthenticationPrincipal final LoginUser loginUser) {
		
		System.out.println("rest");
		System.out.println(loginUser);
		
		
		List<Item> itemList = topService.getItemsByPage(from, to, name);

		
		return itemList;
	}
}
