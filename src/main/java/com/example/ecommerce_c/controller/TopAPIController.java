package com.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_c.domain.Item;
import com.example.ecommerce_c.service.TopService;

@Controller
@RequestMapping("")
public class TopAPIController {
	
	@Autowired
	TopService topService;
	
	@RequestMapping("/getItemByPage")
	public List<Item> getItemsByPage(int from, int to, String name) {
		return topService.getItemsByPage(from, to, name);
	}
	
	@RequestMapping("")
	public String getTop() {
		return "item_list_toy";
	}

}
