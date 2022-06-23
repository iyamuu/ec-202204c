package com.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.form.PageForm;
import com.example.ecommerce_c.security.LoginUser;
import com.example.ecommerce_c.service.TopService;

/**
 * 商品情報を取得するAPIコントローラ.
 * 
 * @author hvthinh
 *
 */
@RestController
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
	@PostMapping("/getItemByPage")
	public List<Item> getItemsByPage(@RequestBody PageForm form, @AuthenticationPrincipal final LoginUser loginUser) {
		
		
		//ログインユーザなら絞り込み情報がある、ゲストなら絞り込みなし
		GiftInformation giftInformation;
		if(loginUser == null) {
			giftInformation = new GiftInformation();  //全商品が対象となるような絞り込み情報
		}else {
			giftInformation = topService.getGiftInfoByUserId(loginUser.getUserId());
		}
		
		
		
		List<Item> itemList;
		if(form.getName() == null) {
			itemList = topService.getItemsByPage(form.getFrom(), form.getTo(), giftInformation);
		}else {
			itemList = topService.searchByName(form.getFrom(), form.getTo(), form.getName()); 
		}

		return itemList;
	}
}
