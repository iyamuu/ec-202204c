package com.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.repository.OrderRepository;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;

/**
 * 
 * Lineでメッセージ送信処理を行う Service,
 * @author teranishidaina
 *
 */
@Service
public class LineMessageService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	/**
	 * 注文完了メッセージを生成する.
	 * 
	 * @param orderId 購入が完了した注文ID
	 * @return
	 */
	public Carousel getCompleteMessage(Integer orderId) {
		
		Order order = orderRepository.findById(orderId);
		
		List<Bubble> orderItemMessage = new ArrayList<>();
		
		return null;
	}
}
